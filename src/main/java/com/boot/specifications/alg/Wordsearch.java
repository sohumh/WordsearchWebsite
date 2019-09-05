package com.boot.specifications.alg;

import java.util.Random;
import java.util.*;

public class Wordsearch {
    static PriorityQueue<Gap> horHeap; //Stores the horizontal gaps
    static PriorityQueue<Gap> vertHeap; //See above, but vertical
    static PriorityQueue<Gap> descHeap; // !HOR & DIAG
    static PriorityQueue<Gap> ascHeap; // HOR AND DIAG
    static HashMap<Tile, HashSet<Gap>> map; //Each tile piece has a set number of available words it can be included in. this map stores all the open tiles
    public static char[][] finalAnswer;
    static Tile[][] grid;
    static int width;
    static int height;
    static String message;
    static Random rand;
    static gapComparator comparator;
    static char filler = '.';
    static boolean failure;
    public static int timesVert = 0;
    public static int timesHor = 0;
    public static int timesAsc = 0;
    public static int timesDesc = 0;
    static int repeatsNeeded;
    static LinkedList<String> words;
    static boolean finishMode;
    static int spacesNeededToFill;
    public static HashMap<String, Integer[]> locationsPlaced;
    public static LinkedList<String> seen;
    public static int tries;


    //TODO: Properly add repeats mode to the normal wordsearch -> things to consider
    // 1) Make remove word properly work
    // 2) Think about when we turn finish mode on, no more overlapping should happen
    // 3) Remove the words properly, just run it and see what is happening


    public Wordsearch(int r, int c, String hiddenMessage, int seed, int t) {
        rand = new Random(seed);
        comparator = new gapComparator(rand);
        horHeap = new PriorityQueue<>(comparator);
        vertHeap = new PriorityQueue<>(comparator);
        descHeap = new PriorityQueue<>(comparator);
        ascHeap = new PriorityQueue<>(comparator);
        map = new HashMap<>();
        width = c;
        height = r;
        message = hiddenMessage;
        failure = false;
        finishMode = false;
        locationsPlaced = new HashMap<>();
        seen = new LinkedList<>();
        tries = t;
    }

    public static void showGrid(boolean show) { //Pass in the args needed for the Wordsearch constructor
        if (show) {
            translateTilestoChar();
            printSearch(finalAnswer);
        }
    }
    //TYPES OF RUNS


    public static int getHeapTop(PriorityQueue<Gap> heap) {
        if (heap.isEmpty()) {
            return 0;
        } else {
            return heap.peek().getSize();
        }
    }

    //Removal and Reinsertion Steps

    public static void nullifyTile(Tile tile, boolean hor, boolean diag, HashMap<Tile, HashSet<Gap>> map, HashMap<Tile, TreeSet<Gap>> allGaps,
                                   PriorityQueue<Gap> horHeap, PriorityQueue<Gap> vertHeap, PriorityQueue<Gap> ascHeap, PriorityQueue<Gap> descHeap) {
        Iterator<Gap> iter = map.get(tile).iterator();

        if (allGaps != null) {
            allGaps.put(tile, new TreeSet<>());
        }
        while (iter.hasNext()) {
            Gap gap = iter.next();
            if (allGaps != null) {
                allGaps.get(tile).add(gap);
            }
            iter.remove();
            delink(gap, map); //TODO: change delink
            if (gap.hor == hor && gap.diag == diag) {
                //We don't want to remove any gaps that were the same direction as the one we just removed
                continue;
            }
            if (gap.hor && gap.diag) { // ASC
                ascHeap.remove(gap);
                addLeftoverGapsAsc(tile, gap, ascHeap, map);
            } else if (gap.hor) { // HORIZONTAL
                horHeap.remove(gap);
                addLeftoverGapsHor(tile, gap, horHeap, map);
                //TODO: THE PROBLEM IS THAT WE ADD A LEFTOVER GAP HERE, BUT OUR COLLISION INSERTION OVERRIDES THIS.
                // THE OVERLAPPED GAP MUST BE DELETED WHEN THE COLLISION INSERTION OCCURS.
            } else if (gap.diag) { //DESC
                descHeap.remove(gap);
                addLeftoverGapsDesc(tile, gap, descHeap, map);
            } else { //VERT
                vertHeap.remove(gap);
                addLeftoverGapsVert(tile, gap, vertHeap, map);
            }
        }
        map.remove(tile); //This is why a tile could be null
    }

    public static void removeVertLeftoverGapsFromHeap(Gap gap, int skip) {
        int[] firstEnd = new int[]{gap.start[0] + skip - 1, gap.start[1]};
        int[] secondStart = new int[]{gap.start[0] + skip + 1, gap.start[1]};
        Gap first = new Gap(gap.start, firstEnd, false, false);
        Gap second = new Gap(secondStart, gap.end, false, false);
        if (vertHeap.remove(first)) {
            delink(first, map);
        }
        if (vertHeap.remove(second)) {
            delink(second, map);
        }

    }

    public static void removeDescLeftoverGapsFromHeap(Gap gap, int skip) {
        int[] firstEnd = new int[]{gap.start[0] + skip - 1, gap.start[1] + skip - 1};
        int[] secondStart = new int[]{gap.start[0] + skip + 1, gap.start[1] + skip + 1};
        Gap first = new Gap(gap.start, firstEnd, false, true);
        Gap second = new Gap(secondStart, gap.end, false, true);
        if (descHeap.remove(first)) {
            delink(first, map);
        }
        if (descHeap.remove(second)) {
            delink(second, map);
        }
    }

    public static void removeAscLeftoverGapsFromHeap(Gap gap, int skip) {
        int[] firstEnd = new int[]{gap.start[0] - skip + 1, gap.start[1] + skip - 1};
        int[] secondStart = new int[]{gap.start[0] - skip - 1, gap.start[1] + skip + 1};
        Gap first = new Gap(gap.start, firstEnd, true, true);
        Gap second = new Gap(secondStart, gap.end, true, true);
        if (ascHeap.remove(first)) {
            delink(first, map);
        }
        if (ascHeap.remove(second)) {
            delink(second, map);
        }
    }

    public static void removeHorLeftoverGapsFromHeap(Gap gap, int skip) {
        int[] firstEnd = new int[]{gap.start[0], gap.start[1] + skip - 1};
        int[] secondStart = new int[]{gap.start[0], gap.start[1] + skip + 1};
        Gap first = new Gap(gap.start, firstEnd, true, false);
        Gap second = new Gap(secondStart, gap.end, true, false);
        if (horHeap.remove(first)) {
            delink(first, map);
        }
        if (horHeap.remove(second)) {
            delink(second, map);
        }
    }

    public static void removeGapFromHeap(Gap gap) {
        if (gap.hor && gap.diag) { // ASC
            ascHeap.remove(gap);
        } else if (gap.hor) { // HORIZONTAL
            horHeap.remove(gap);
        } else if (gap.diag) { //DESC
            descHeap.remove(gap);
        } else { //VERT
            vertHeap.remove(gap);
        }
    }

    public static boolean checkSpacesNeededToFill() {
        return spacesNeededToFill < 20;
    }

    //We want to check if the gap is still valid, i.e. there is only one char in the gap
    public static int validityCheck(Gap gap, Tile tile) {
        int ans = -1;
        if (gap.hor && gap.diag) { // ASC
            for (int i = 0; i < gap.getSize(); i++) {
                if (tile.row == gap.start[0] - i && tile.col == gap.start[1] + i) {
                    ans = i;
                } else if (grid[gap.start[0] - i][gap.start[1] + i].character != filler) {
                    return -1;
                }
            }
        } else if (gap.hor) { // HORIZONTAL
            for (int c = gap.start[1]; c <= gap.end[1]; c++) {
                if (tile.row == gap.start[0] && tile.col == c) {
                    ans = c - gap.start[1];
                } else if (grid[gap.start[0]][c].character != filler) {
                    return -1;
                }
            }
        } else if (gap.diag) { //DESC
            for (int i = 0; i < gap.getSize(); i++) {
                if (tile.row == gap.start[0] + i && tile.col == gap.start[1] + i) {
                    ans = i;

                } else if (grid[gap.start[0] + i][gap.start[1] + i].character != filler) {
                    return -1;
                }
            }
        } else { //VERT
            for (int r = gap.start[0]; r <= gap.end[0]; r++) {
                if (tile.row == r && tile.col == gap.start[1]) {
                    ans = r - gap.start[0];
                } else if (grid[r][gap.start[1]].character != filler) {
                    return -1;
                }
            }
        }
        return ans;
    }

    public static void addVertLeftoverWordGaps(Gap gap, int insertCoord, int size) {
        Gap first = new Gap(gap.start, new int[]{insertCoord - 1, gap.start[1]}, false, false);
        Gap second = new Gap(new int[]{insertCoord + size, gap.start[1]}, gap.end, false, false);
        vertGapsInsert(first, second, vertHeap, map);
    }

    public static void addHorLeftoverWordGaps(Gap gap, int insertCoord, int size) {
        Gap first = new Gap(gap.start, new int[]{gap.start[0], insertCoord - 1}, true, false);
        Gap second = new Gap(new int[]{gap.start[0], insertCoord + size}, gap.end, true, false);
        horGapsInsert(first, second, horHeap, map);
    }

    public static void addAscLeftoverWordGaps(Gap gap, int[] startCoord, int size) {
        Gap first = new Gap(gap.start, new int[]{startCoord[0] + 1, startCoord[1] - 1}, true, true);
        Gap second = new Gap(new int[]{startCoord[0] - size, startCoord[1] + size}, gap.end, true, true);
        ascGapsInsert(first, second, ascHeap, map);
    }

    public static void addDescLeftoverWordGaps(Gap gap, int[] startCoord, int size) {
        Gap first = new Gap(gap.start, new int[]{startCoord[0] - 1, startCoord[1] - 1}, false, true);
        Gap second = new Gap(new int[]{startCoord[0] + size, startCoord[1] + size}, gap.end, false, true);
        descGapsInsert(first, second, descHeap, map);
    }

    public static void addLeftoverGapsVert(Tile tile, Gap gap, PriorityQueue<Gap> vertHeap, HashMap<Tile, HashSet<Gap>> map) {
        Gap first = new Gap(gap.start, new int[]{tile.row - 1, tile.col}, false, false);
        Gap second = new Gap(new int[]{tile.row + 1, tile.col}, gap.end, false, false);
        vertGapsInsert(first, second, vertHeap, map);
    }

    public static void vertGapsInsert(Gap first, Gap second, PriorityQueue<Gap> vertHeap, HashMap<Tile, HashSet<Gap>> map) {
        insertVertGap(first, vertHeap, map);
        insertVertGap(second, vertHeap, map);
    }

    public static void insertVertGap(Gap gap, PriorityQueue<Gap> vertHeap, HashMap<Tile, HashSet<Gap>> map) {
        if (gap.getSize() >= 3) {
            vertHeap.add(gap);
            vertFillMap(gap, map);
        }
    }

    public static void addLeftoverGapsHor(Tile tile, Gap gap, PriorityQueue<Gap> horHeap, HashMap<Tile, HashSet<Gap>> map) {
        Gap first = new Gap(gap.start, new int[]{tile.row, tile.col - 1}, true, false);
        Gap second = new Gap(new int[]{tile.row, tile.col + 1}, gap.end, true, false);
        horGapsInsert(first, second, horHeap, map);
    }

    public static void horGapsInsert(Gap first, Gap second, PriorityQueue<Gap> horHeap, HashMap<Tile, HashSet<Gap>> map) {
        insertHorGap(first, horHeap, map);
        insertHorGap(second, horHeap, map);
    }

    public static void insertHorGap(Gap gap, PriorityQueue<Gap> horHeap, HashMap<Tile, HashSet<Gap>> map) {
        if (gap.getSize() >= 3) {
            horHeap.add(gap);
            horFillMap(gap, map);
        }
    }

    public static void addLeftoverGapsDesc(Tile tile, Gap gap, PriorityQueue<Gap> descHeap, HashMap<Tile, HashSet<Gap>> map) {
        Gap first = new Gap(gap.start, new int[]{tile.row - 1, tile.col - 1}, false, true);
        Gap second = new Gap(new int[]{tile.row + 1, tile.col + 1}, gap.end, false, true);
        descGapsInsert(first, second, descHeap, map);
    }

    public static void descGapsInsert(Gap first, Gap second, PriorityQueue<Gap> descHeap, HashMap<Tile, HashSet<Gap>> map) {
        insertDescGap(first, descHeap, map);
        insertDescGap(second, descHeap, map);
    }

    public static void insertDescGap(Gap gap, PriorityQueue<Gap> descHeap, HashMap<Tile, HashSet<Gap>> map) {
        if (gap.getSize() >= 3) {
            descHeap.add(gap);
            descFillMap(gap, map);
        }
    }

    public static void addLeftoverGapsAsc(Tile tile, Gap gap, PriorityQueue<Gap> ascHeap, HashMap<Tile, HashSet<Gap>> map) {
        Gap first = new Gap(gap.start, new int[]{tile.row + 1, tile.col - 1}, true, true);
        Gap second = new Gap(new int[]{tile.row - 1, tile.col + 1}, gap.end, true, true);
        ascGapsInsert(first, second, ascHeap, map);
    }

    public static void ascGapsInsert(Gap first, Gap second, PriorityQueue<Gap> ascHeap, HashMap<Tile, HashSet<Gap>> map) {
        insertAscGap(first, ascHeap, map);
        insertAscGap(second, ascHeap, map);
    }

    public static void insertAscGap(Gap gap, PriorityQueue<Gap> ascHeap, HashMap<Tile, HashSet<Gap>> map) {
        if (gap.getSize() >= 3) {
            ascHeap.add(gap);
            ascFillMap(gap, map);
        }
    }

    public static void delink(Gap gap, HashMap<Tile, HashSet<Gap>> map) {
        if (gap.hor && gap.diag) { // ASC
            for (int x = 0; x < gap.getSize(); x++) {
                map.get(grid[gap.start[0] - x][gap.start[1] + x]).remove(gap);
            }
        } else if (gap.hor) { // HORIZONTAL
            for (int c = gap.start[1]; c <= gap.end[1]; c++) {
                map.get(grid[gap.start[0]][c]).remove(gap);
            }
        } else if (gap.diag) { //DESC
            for (int x = 0; x < gap.getSize(); x++) {
                map.get(grid[gap.start[0] + x][gap.start[1] + x]).remove(gap);
            }
        } else { //VERT
            for (int r = gap.start[0]; r <= gap.end[0]; r++) {
                map.get(grid[r][gap.start[1]]).remove(gap);
            }
        }
    }

    //Word Constructors

    // Construction/Alteration of Data Structures

    public static void addToDescHeap() {
        int minVal = Math.min(width, height);
        int r;
        for (r = 0; r < height - width; r++) { //Adding all the gaps that start in first column and end in the last column
            int[] start = new int[]{r, 0};
            int[] end = new int[]{r + width - 1, width - 1};
            Gap gap = new Gap(start, end, false, true);
            if (gap.getSize() < 3) {
                break;
            }
            descFillMap(gap, map);
            descHeap.add(gap);

        }
        int count = 1;
        for (r = r; r < height - 2; r++) { //Adding all the gaps that start in first column and end in the last row
            int[] start = new int[]{r, 0};
            int[] end = new int[]{height - 1, minVal - count};
            count++;
            Gap gap = new Gap(start, end, false, true);
            if (gap.getSize() < 3) {
                break;
            }
            try {
            	descFillMap(gap, map);
            } catch (ArrayIndexOutOfBoundsException n) {
            	System.out.println("_________");
            	System.out.println(width);
            	System.out.println(height);
            	System.out.println(gap);
            	printMap(map);
            	for (String f: words) {
            		System.out.println(f);
            	}
            	System.out.println(message);
            	System.exit(1);
            }
            descHeap.add(gap);
        }
        int c;
        for (c = 1; c < width - height; c++) { //Adding all gaps that start in the first row and end in the last row
            int[] start = new int[]{0, c};
            int[] end = new int[]{height - 1, height + c - 1};
            Gap gap = new Gap(start, end, false, true);
            if (gap.getSize() < 3) {
                break;
            }
            descFillMap(gap, map);
            descHeap.add(gap);
        }
        int newCount;
        if (width > height) {
            newCount = 1;
        } else {
            newCount = 2;
        }
        for (c = c; c < width - 2; c++) { //Adding all gaps that start in the first row and end in the last column
            int[] start = new int[]{0, c};
            int[] end = new int[]{minVal - newCount, width - 1};
            Gap gap = new Gap(start, end, false, true);
            if (gap.getSize() < 3) {
                break;
            }
            newCount++;
            descFillMap(gap, map);
            descHeap.add(gap);
        }
    }

    public static void descFillMap(Gap gap, HashMap<Tile, HashSet<Gap>> map) {
        for (int i = 0; i < gap.getSize(); i++) {
            if (map.containsKey(grid[gap.start[0] + i][gap.start[1] + i])) {
                map.get(grid[gap.start[0] + i][gap.start[1] + i]).add(gap);
            }
        }
    }

    public static void addToAscHeap() {
        int minVal = Math.min(width, height);
        int r;
        for (r = 0; r < height - width; r++) { //Adding all the gaps that start in first column and end in the last column
            int[] start = new int[]{height - 1 - r, 0};
            int[] end = new int[]{height - r - width, width - 1};
            Gap gap = new Gap(start, end, true, true);
            if (gap.getSize() < 3) {
                break;
            }
            ascFillMap(gap, map);
            ascHeap.add(gap);
        }
        int count = 1;
        for (r = r; r < height - 2; r++) { //Adding all the gaps that start in first column and end in the first row
            int[] start = new int[]{height - 1 - r, 0};
            int[] end = new int[]{0, minVal - count};
            count++;
            Gap gap = new Gap(start, end, true, true);
            if (gap.getSize() < 3) {
                break;
            }
            ascFillMap(gap, map);
            ascHeap.add(gap);
        }
        int c;
        for (c = 1; c < width - height; c++) { //Adding all gaps that start in the last row and end in the first row
            int[] start = new int[]{height - 1, c};
            int[] end = new int[]{0, height + c - 1};
            Gap gap = new Gap(start, end, true, true);
            if (gap.getSize() < 3) {
                break;
            }
            ascFillMap(gap, map);
            ascHeap.add(gap);
        }
        int newCount;
        if (width > height) {
            newCount = 1;
        } else {
            newCount = 2;
        }
        for (c = c; c < width - 2; c++) { //Adding all gaps that start in the last row and end in the first column
            int[] start = new int[]{height - 1, c};
            int[] end = new int[]{height - 1 - minVal + newCount, width - 1};
            Gap gap = new Gap(start, end, true, true);
            if (gap.getSize() < 3) {
                break;
            }
            newCount++;
            ascFillMap(gap, map);
            ascHeap.add(gap);
        }
    }

    public static void ascFillMap(Gap gap, HashMap<Tile, HashSet<Gap>> map) {
        for (int i = 0; i < gap.getSize(); i++) {
            if (map.containsKey(grid[gap.start[0] - i][gap.start[1] + i])) {
                map.get(grid[gap.start[0] - i][gap.start[1] + i]).add(gap);
            }
        }
    }

    public static void addToHorHeap() {
        for (int r = 0; r < height; r++) {
            int[] start = new int[]{r, 0};
            int[] end = new int[]{r, width - 1};
            Gap gap = new Gap(start, end, true, false);
            horFillMap(gap, map);
            horHeap.add(gap);
        }
    }

    public static void addToVertHeap() {
        for (int c = 0; c < width; c++) {
            int[] start = new int[]{0, c};
            int[] end = new int[]{height - 1, c};
            Gap gap = new Gap(start, end, false, false);
            vertFillMap(gap, map);
            vertHeap.add(gap);
        }
    }

    public static void horFillMap(Gap gap, HashMap<Tile, HashSet<Gap>> map) {
        for (int c = gap.start[1]; c <= gap.end[1]; c++) {
            if (map.containsKey(grid[gap.start[0]][c])) {
                map.get(grid[gap.start[0]][c]).add(gap);
            }
        }
    }

    public static void vertFillMap(Gap gap, HashMap<Tile, HashSet<Gap>> map) {
        for (int r = gap.start[0]; r <= gap.end[0]; r++) {
            if (map.containsKey(grid[r][gap.start[1]])) {
                map.get(grid[r][gap.start[1]]).add(gap);
            }
        }
    }


    public static void addToLocationsMap(String word, int[] start, int[] end) {
        Integer[] answer = new Integer[4];
        answer[0] = start[0];
        answer[1] = start[1];
        answer[2] = end[0];
        answer[3] = end[1];
        locationsPlaced.put(word, answer);
    }



    //Final Wordsearch Creation

    public static void translateTilestoChar() {
        //We can just insert the final message while we are printing the final answer.
        int count = 0;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                Tile curr = grid[r][c];

                if (curr.character == '.' && count < message.length()) {
                    finalAnswer[r][c] = message.charAt(count);
                    count++;
                } else {
                    finalAnswer[r][c] = curr.character;
                }
            }
        }
    }

    public static void printBoxSearch(char[][] table) {
        for (int i = 0; i < table[0].length + 4; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print("| ");
            System.out.print(table[i]);
            System.out.println(" |");
        }
        for (int i = 0; i < table[0].length + 4; i++) {
            System.out.print("-");
        }
    }

    public static void printSearch(char[][] table) {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i]);
        }
    }


    //Debugging methods

    public static LinkedList<String> duplicateLinkedList(LinkedList<String> strings) {
        LinkedList<String> answer = new LinkedList<>();
        for (String s : strings) {
            answer.addFirst(s);
        }
        return answer;
    }


    public static void printMap(HashMap<Tile, HashSet<Gap>> map) {
        System.out.println("_______________________________________");
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                System.out.println(" r " + r + " c " + c + " " + map.get(grid[r][c]));
            }
        }
    }

    public static void printHeap(PriorityQueue<Gap> heap) {
        for (Gap g : heap) {
            System.out.println(g);
        }
        System.out.println();
    }

    public static void printHeaps(PriorityQueue<Gap> one, PriorityQueue<Gap> two) {
        System.out.println("_________");
        printHeap(one);
        System.out.println("_________");
        printHeap(two);
        System.out.println("_________");
    }
}
