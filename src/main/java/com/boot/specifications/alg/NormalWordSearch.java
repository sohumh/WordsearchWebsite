package com.boot.specifications.alg;

import java.util.*;

public class NormalWordSearch extends Wordsearch {
    static boolean normalMode;
    static boolean maximizedMode;
    static int horMid; //min, max
    static int vertMid;
    static Word dictionary;
    static int category;
    static Stack<Integer> stack;
    static boolean[] categories;
    static HashMap<Character, HashMap<Integer, LinkedList<String>>> letterMap;


    public NormalWordSearch(int r, int c, String hiddenMessage, int seed, boolean maxRepeats, boolean[] categs, int tries) {
        super(r, c, hiddenMessage, seed, tries);
        categories = categs;
        dictionary = new Word(categories, rand);
        letterMap = dictionary.generateLetterMap();
        if (maxRepeats) {
            repeatsNeeded = width * height;
        } else {
            repeatsNeeded = 0;
        }
        spacesNeededToFill = width * height - message.length();
        normalMode = true;
        maximizedMode = false;
        horMid = Math.max(4, width / 2); // middle
        vertMid = Math.max(4, height / 2);
        category = 0;
        stack = null;
        grid = new Tile[r][c];
        finalAnswer = new char[r][c];
    }

    public static boolean run(boolean show) { //Pass in the args needed for the Wordsearch constructor
        boolean ans = wordSearchAlg();
        Wordsearch.showGrid(show);
        return ans;
    }

    public static boolean wordSearchAlg() {
        setUpDataStructures();
        while (spacesNeededToFill > 0 && !failure) {
            shouldWeTurnOnFinishMode();
            if (normalMode) {
                normalRun();
            } else if (maximizedMode) {
                maximizedRun();
            } else {
                return false;
            }
        }
        return spacesNeededToFill == 0;
    }

    //We are using this word, so we must remove it from the overall list and from all the hashsets
    public static void removeWord(String word) {
        seen.add(word);
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            letterMap.get(curr).get(word.length()).remove(word);
        }
    }

    public static void setUpDataStructures() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                Tile newTile = new Tile(r, c, filler, null);
                map.put(newTile, new HashSet<>());
                grid[r][c] = newTile;
            }
        }
        addToHorHeap();
        addToVertHeap();
        addToAscHeap();
        addToDescHeap();
    }

    public static void shouldWeTurnOnFinishMode() {
        if (checkSpacesNeededToFill()) {
            if (!finishMode && (normalMode || maximizedMode)) {
                stack = constructSeriesOfMoves(spacesNeededToFill);
                if (stack == null) {
                    finishMode = false;
                    maximizedMode = false;
                    repeatsNeeded = 0;
                } else {
                    finishMode = true;
                    maximizedMode = true;
                    repeatsNeeded = 0;
                }
                normalMode = false;
            }
        }
    }

    public static void normalRun() {
        int num = rand.nextInt(2);
        if (num == 0) {
            if (ascHeapRemoval()) {
                return;
            }
        } else {
            if (descHeapRemoval()) {
                return;
            }
        }
    }

    //HEAP REPEATED ACTIONS
    public static boolean ascHeapRemoval() {
        return diagHeapRemovalSteps(false, ascHeap);
    }

    public static boolean descHeapRemoval() {
        return diagHeapRemovalSteps(true, descHeap);
    }

    public static boolean diagHeapRemovalSteps(boolean desc, PriorityQueue<Gap> heap) {
        if (!finishMode && checkSpacesNeededToFill()) {
            return true;
        }
        Gap gap = heap.poll();
        //System.out.println(gap);
        boolean forwards = rand.nextBoolean();

        //System.out.println(gap);
        if (gap == null) {
            maximizedMode = true;
            normalMode = false;
            return true;
        }
        int size;
        String word;
        int change;
        int[] tempData = getSizeandChange(gap);
        if (tempData == null) {
            maximizedMode = true;
            normalMode = false;
            return true;
        }
        size = tempData[0]; //TODO: play with this
        change = tempData[1];
        word = dictionary.getWord(size, forwards);
        int startCoord[];
        //Removal process: We only care about reinserting gaps in the opposite direction of the gap we remove
        //Except for the "leftover" gaps —
        if (desc) {
            startCoord = new int[]{gap.start[0] + change, gap.start[1] + change};  //Randomly choose start
            addDescLeftoverWordGaps(gap, startCoord, size);
        } else {
            startCoord = new int[]{gap.start[0] - change, gap.start[1] + change};
            addAscLeftoverWordGaps(gap, startCoord, size);
        }
        //dictionary.getWord(size, nextCategory(), forwards); //flips word if direction is backwards
        HashMap<Tile, TreeSet<Gap>> allGaps = null;
        if (!finishMode) {
            allGaps = new HashMap<>();
        }
        for (int x = 0; x < size; x++) {
            Tile curr;
            if (desc) {
                curr = grid[startCoord[0] + x][startCoord[1] + x];
            } else {
                curr = grid[startCoord[0] - x][startCoord[1] + x];
            }
            curr.character = word.charAt(x);
            nullifyTile(curr, gap.hor, gap.diag, map, allGaps, horHeap, vertHeap, ascHeap, descHeap);
        }
        if (finishMode) {
            for (int x = size; x < gap.getSize(); x++) {
                Tile curr;
                if (desc) {
                    curr = grid[startCoord[0] + x][startCoord[1] + x];
                } else {
                    curr = grid[startCoord[0] - x][startCoord[1] + x];
                }
                nullifyTile(curr, gap.hor, gap.diag, map, null, horHeap, vertHeap, ascHeap, descHeap);
            }
        }
        if (desc) {
            addToLocationsMap(word, startCoord, new int[]{startCoord[0] + size - 1, startCoord[1] + size - 1});
        } else {
            addToLocationsMap(word, startCoord, new int[]{startCoord[0] - size + 1, startCoord[1] + size - 1});
        }
        if (!finishMode) {
            addressFasterCollisions(allGaps, word);
        }
        spacesNeededToFill -= size;
        return false;
    }


    public static void horHeapRemoval() {
        commonHeapRemovalSteps(horHeap, horMid, 1);
    }

    public static void vertHeapRemoval() {
        commonHeapRemovalSteps(vertHeap, vertMid, 0);
    }

    public static void commonHeapRemovalSteps(PriorityQueue<Gap> heap, int mid, int index) {
        if (!finishMode && checkSpacesNeededToFill()) {
            return;
        }
        Gap gap = heap.poll();
        //System.out.println(gap);
        if (gap == null) {
            maximizedMode = true;
            normalMode = false;
            return;
        }
        boolean forwards = rand.nextBoolean();
        String word;
        int size;
        int insertCoord;
        size = getSize(gap, mid);
        if (size == -1) {
            return;
        }
        word = dictionary.getWord(size, forwards);
        if (finishMode) {
            insertCoord = gap.start[index];
        } else {
            insertCoord = gap.start[index] + rand.nextInt(gap.getSize() - size + 1);
        }
        //dictionary.getWord(size, nextCategory(), forwards); //flips word if direction is backwards
        //Removal process: We only care about reinserting gaps in the opposite direction of the gap we remove
        //Except for the "leftover" gaps —
        if (gap.hor) {
            addHorLeftoverWordGaps(gap, insertCoord, size);
        } else {
            addVertLeftoverWordGaps(gap, insertCoord, size);
        }

        HashMap<Tile, TreeSet<Gap>> allGaps = null;
        if (!finishMode) {
            allGaps = new HashMap<>();
        }
        for (int x = 0; x < size; x++) {
            Tile curr;
            if (gap.hor) {
                curr = grid[gap.start[0]][insertCoord + x];
            } else {
                try {
                    curr = grid[insertCoord + x][gap.start[1]];
                } catch (ArrayIndexOutOfBoundsException v) {
                    System.out.println(word);
                    printMap(map);
                    System.out.println(gap);
                    System.out.println(width);
                    System.out.println(height);
                    System.out.println(message);
                    System.out.println(finishMode);
                    failure = true;
                    return;
                }
            }
            curr.character = word.charAt(x);
            try {
                nullifyTile(curr, gap.hor, gap.diag, map, allGaps, horHeap, vertHeap, ascHeap, descHeap);
            } catch (NullPointerException n) {
                System.out.println(word);
                printMap(map);
                System.out.println(gap);
                System.out.println(curr);
                System.out.println(width);
                System.out.println(height);
                System.out.println(message);
                System.out.println(finishMode);
                failure = true;
                return;
            }
        }
        if (finishMode) {
            for (int x = size; x < gap.getSize(); x++) {
                Tile curr;
                if (gap.hor) {
                    curr = grid[gap.start[0]][insertCoord + x];
                } else {
                    curr = grid[insertCoord + x][gap.start[1]];
                }
                nullifyTile(curr, gap.hor, gap.diag, map, null, horHeap, vertHeap, ascHeap, descHeap);
            }
        }
        if (gap.hor) {
            addToLocationsMap(word, new int[]{gap.start[0], insertCoord}, new int[]{gap.start[0], insertCoord + size - 1});
        } else {
            addToLocationsMap(word, new int[]{insertCoord, gap.start[1]}, new int[]{insertCoord + size - 1, gap.start[1]});
        }
        if (!finishMode) {
            addressFasterCollisions(allGaps, word);
        }
        spacesNeededToFill -= size;
    }


    public static void insertCollisionWord(String word, Gap gap, int spot, int skip) {
        spacesNeededToFill -= word.length();
        spacesNeededToFill += 1;

        if (gap.hor && gap.diag) { // ASC
            //If any leftover gaps were added that are now overridden, they must be deleted.
            removeAscLeftoverGapsFromHeap(gap, skip);
            addAscLeftoverWordGaps(gap, new int[]{gap.start[0] - spot, gap.start[1] + spot}, word.length());
        } else if (gap.hor) { // HORIZONTAL
            removeHorLeftoverGapsFromHeap(gap, skip);
            addHorLeftoverWordGaps(gap, gap.start[1] + spot, word.length());

        } else if (gap.diag) { //DESC
            removeDescLeftoverGapsFromHeap(gap, skip);
            addDescLeftoverWordGaps(gap, new int[]{gap.start[0] + spot, gap.start[1] + spot}, word.length());
        } else { //VERT
            removeVertLeftoverGapsFromHeap(gap, skip);
            addVertLeftoverWordGaps(gap, gap.start[0] + spot, word.length());
        }
        repeatsNeeded--;
        HashMap<Tile, TreeSet<Gap>> allGaps = null;
        if (!finishMode) {
            allGaps = new HashMap<>();
        }

        for (int x = 0; x < word.length(); x++) {
            if (spot + x == skip) {
                continue;
            }
            int i = spot + x;
            int r;
            int c;
            if (gap.hor && gap.diag) { // ASC
                r = gap.start[0] - i;
                c = gap.start[1] + i;
            } else if (gap.hor) { // HORIZONTAL
                r = gap.start[0];
                c = gap.start[1] + i;
            } else if (gap.diag) { //DESC
                r = gap.start[0] + i;
                c = gap.start[1] + i;
            } else { //VERT
                r = gap.start[0] + i;
                c = gap.start[1];
            }
            Tile curr = grid[r][c];
            curr.character = word.charAt(x);
            //System.out.println("Inserting "+ curr.character + " at " + r + " " + c );
            nullifyTile(curr, gap.hor, gap.diag, map, allGaps, horHeap, vertHeap, ascHeap, descHeap);
        }
        if (gap.hor && gap.diag) { // ASC
            addToLocationsMap(word, new int[]{gap.start[0] - spot, gap.start[1] + spot}, new int[]{gap.start[0] - spot - word.length() + 1, gap.start[1] + spot + word.length() - 1});
        } else if (gap.hor) { // HORIZONTAL
            addToLocationsMap(word, new int[]{gap.start[0], gap.start[1] + spot}, new int[]{gap.start[0], gap.start[1] + spot + word.length() - 1});
        } else if (gap.diag) { //DESC
            addToLocationsMap(word, new int[]{gap.start[0] + spot, gap.start[1] + spot}, new int[]{gap.start[0] + spot + word.length() - 1, gap.start[1] + spot + word.length() - 1});
        } else { //VERT
            addToLocationsMap(word, new int[]{gap.start[0] + spot, gap.start[1]}, new int[]{gap.start[0] + spot + word.length() - 1, gap.start[1]});
        }
        if (!finishMode) {
            addressFasterCollisions(allGaps, word);
        }
    }

    public static void addressCollisions(HashMap<Tile, TreeSet<Gap>> allGaps, String word) {
        //Our goal is to iterate through the tiles in the words by the ones that are the best.
        //The best tile is the one that has the largest possible leftover word
        if (checkSpacesNeededToFill() || repeatsNeeded == 0 || finishMode) {
            return;
        }

        while (allGaps.size() > 0) {
            TreeMap<Tile, Integer> treeMap = new TreeMap<>();
            for (Tile tile : allGaps.keySet()) {
                treeMap.put(tile, tile.score());
            }

            //We iterate through the tiles by the ones that have the highest words associated with them
            for (Tile tile : treeMap.keySet()) {
                TreeSet<Gap> gaps = allGaps.remove(tile);
                boolean inserted = false;
                for (Gap gap : gaps) {
                    if (repeatsNeeded > 0) {
                        int spot = validityCheck(gap, tile);
                        if (spot != -1) {
                            if (tryToInsertCollisions(gap, tile, spot)) {
                                inserted = true;
                            }
                        }
                    }
                }
                if (inserted) {
                    //When something is inserted, then the state of the wordsearch changes, and we must reset treemap
                    break;
                }
            }
        }
    }

    public static void addressFasterCollisions(HashMap<Tile, TreeSet<Gap>> allGaps, String word) {
        LinkedList<Tile> keys = new LinkedList<>(allGaps.keySet());
        while (!keys.isEmpty()) {
            Tile tile = keys.remove(rand.nextInt(keys.size()));
            TreeSet<Gap> gaps = allGaps.get(tile);
            for (Gap gap : gaps) {
                if (repeatsNeeded > 0 && !finishMode) {
                    int spot = validityCheck(gap, tile);
                    if (spot != -1) {
                        tryToInsertCollisions(gap, tile, spot);
                    }
                }
            }
        }
    }

    public static boolean tryToInsertCollisions(Gap gap, Tile tile, int spot) {
        HashMap<Integer, LinkedList<String>> mega = letterMap.get(tile.character);
        boolean inserted = false;
        for (int outer = 5; outer < gap.getSize(); outer++) {
            LinkedList<String> curr = mega.get(outer);
            if (curr == null) {
                continue;
            }
            LinkedList<String> dupl = duplicateLinkedList(curr);
            for (String word : dupl) {
                if (checkSpacesNeededToFill()) {
                    break;
                }
                if (word.length() > gap.getSize() || validityCheck(gap, tile) == -1 || !mega.get(outer).contains(word)) {
                    continue;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == tile.character) {
                        int lettersBefore = i;
                        int lettersLeft = word.length() - i;
                        if (word.length() - spot - lettersLeft >= 0
                                && spot - lettersBefore >= 0) {
                            removeWord(word);
                            insertCollisionWord(word, gap, spot - lettersBefore, spot);
                            inserted = true;
                            break;
                        }
                        if (spot - lettersLeft + 1 >= 0
                                && word.length() - spot - lettersBefore - 1 >= 0) {
                            removeWord(word);
                            insertCollisionWord(Word.reverse(word), gap, spot - lettersLeft + 1, spot);
                            inserted = true;
                            break;
                        }
                    }
                }
            }
        }
        return inserted;
    }

    public static int getSize(Gap gap, int mid) {
        int size;
        if (maximizedMode) {
            if (finishMode) {
                return stack.pop();
            } else {
                size = gap.getSize();
            }
        } else {
            if (gap.getSize() > mid) { //We want to stop doing the normal way when the gaps become less than half
                size = 3 + rand.nextInt(gap.getSize() - mid);
                if (size >= gap.getSize()) {
                    size = gap.getSize();
                }
            } else {
                maximizedMode = true;
                normalMode = false;
                return -1;
            }
        }
        return Math.min(10, size);
    }


    public static int[] getSizeandChange(Gap gap) {
        int size;
        int startCoord[];
        if (maximizedMode) {
            if (finishMode) {
                return new int[]{stack.pop(), 0};
            } else {
                return new int[]{Math.min(gap.getSize(), 10), 0};
            }
        } else {
            int trueMid = Math.min(horMid, vertMid);
            if (gap.getSize() > trueMid) { //We want to stop doing the normal way whe the gaps become less than half
                size = 4 + rand.nextInt(Math.min(gap.getSize() - 3, 7)); //Randomly choose size
                if (size > gap.getSize()) {
                    size = gap.getSize();
                }
                int change = rand.nextInt(gap.getSize() - size + 1);
                return new int[]{Math.min(size, 10), change};
            } else {
                maximizedMode = true;
                normalMode = false;
                return null;
            }
        }
    }

    public static void maximizedRun() {
        if (horHeap.isEmpty() && vertHeap.isEmpty() && ascHeap.isEmpty() && descHeap.isEmpty()) {
            failure = true;
            return;
        } else if (finishMode && stack == null) {
            failure = true;
            return;
        }
        int vertSize = getHeapTop(vertHeap);
        int horSize = getHeapTop(horHeap);
        int descSize = getHeapTop(descHeap);
        int ascSize = getHeapTop(ascHeap);
        int max = Math.max(Math.max(vertSize, horSize), Math.max(descSize, ascSize));
        //System.out.println(max);

        if (descSize == max) {
            descHeapRemoval();
        } else if (ascSize == max) {
            ascHeapRemoval();
        } else if (vertSize == max) {
            vertHeapRemoval();
        } else {
            horHeapRemoval();
        }
    }

    public static Stack<Integer> constructSeriesOfMoves(int spacesNeededToFill) {
        Stack<Gap> gapsSeen = new Stack<>();
        int gapsCount = 0;
        PriorityQueue<Gap>[] hors = doubleCopy(horHeap);
        PriorityQueue<Gap>[] verts = doubleCopy(vertHeap);
        PriorityQueue<Gap>[] ascs = doubleCopy(ascHeap);
        PriorityQueue<Gap>[] descs = doubleCopy(descHeap);
        PriorityQueue<Gap> copyOfHor = hors[0];
        PriorityQueue<Gap> copyOfVert = verts[0];
        PriorityQueue<Gap> copyOfAsc = ascs[0];
        PriorityQueue<Gap> copyOfDesc = descs[0];
        HashMap<Tile, HashSet<Gap>> fakeMap = duplicateMap(map);
        while (gapsCount < spacesNeededToFill) {
            Gap gap;
            PriorityQueue<Gap> heap = bestHeap(copyOfHor, copyOfVert, copyOfAsc, copyOfDesc);
            if (heap == null) {
                return null;
            } else {
                gap = heap.poll();
                if (gap.diag) {
                    diagHeapReplSteps(gap, fakeMap, copyOfHor, copyOfVert, copyOfAsc, copyOfDesc);
                } else {
                    normHeapReplSteps(gap, fakeMap, copyOfHor, copyOfVert, copyOfAsc, copyOfDesc);
                }
            }
            gapsCount += gap.getSize();
            gapsSeen.push(gap);
        }
        horHeap = hors[1];
        vertHeap = verts[1];
        ascHeap = ascs[1];
        descHeap = descs[1];
        return calculateMoves(gapsSeen);
    }

    public static Stack<Integer> calculateMoves(Stack<Gap> gapsSeen) {
        Stack<Integer> moves = new Stack<>();
        LinkedList<Stack<Integer>> options = new LinkedList<>();
        options.addFirst(moves);
        while (!options.isEmpty() && !gapsSeen.empty()) {
            Gap curr = gapsSeen.pop();
            LinkedList<Stack<Integer>> moreOptions = new LinkedList<>();
            if (curr.getSize() >= 4) {
                for (int i = 4; i <= Math.min(curr.getSize(), 10); i++) {
                    for (Stack s : options) {
                        Stack<Integer> dupl = duplicateStack(s);
                        dupl.push(i);
                        moreOptions.addFirst(dupl);
                        int sum = sumStack(dupl);
                        if (sum == spacesNeededToFill) {
                            return dupl;
                        } else if (sum < 0) {
                            moreOptions.remove(dupl);
                        }
                    }
                }
            }
            if (curr.getSize() >= 3) {
                for (Stack s : options) {
                    s.push(3);
                    int sum = sumStack(s);
                    if (sum == spacesNeededToFill) {
                        return s;
                    } else if (sum < 0) {
                        options.remove(s);
                    }
                }
            }
            options.addAll(moreOptions);
        }
        return null;
    }

    public static void normHeapReplSteps(Gap gap, HashMap<Tile, HashSet<Gap>> fakeMap, PriorityQueue<Gap> copyOfHor, PriorityQueue<Gap> copyOfVert, PriorityQueue<Gap> copyOfAsc, PriorityQueue<Gap> copyOfDesc) {
        int index;
        if (gap.hor) {
            index = 1;
        } else {
            index = 0;
        }
        int insertCoord = gap.start[index];
        if (gap.hor) {
            Gap first = new Gap(gap.start, new int[]{gap.start[0], insertCoord - 1}, true, false);
            Gap second = new Gap(new int[]{gap.start[0], insertCoord + gap.getSize()}, gap.end, true, false);
            horGapsInsert(first, second, copyOfHor, fakeMap);
        } else {
            Gap first = new Gap(gap.start, new int[]{insertCoord - 1, gap.start[1]}, false, false);
            Gap second = new Gap(new int[]{insertCoord + gap.getSize(), gap.start[1]}, gap.end, false, false);
            vertGapsInsert(first, second, copyOfVert, fakeMap);
        }

        for (int x = 0; x < gap.getSize(); x++) {
            Tile curr;
            if (gap.hor) {
                curr = grid[gap.start[0]][insertCoord + x];
            } else {
                curr = grid[insertCoord + x][gap.start[1]];
            }
            nullifyTile(curr, gap.hor, gap.diag, fakeMap, null, copyOfHor, copyOfVert, copyOfAsc, copyOfDesc);
        }
    }

    public static void diagHeapReplSteps(Gap gap, HashMap<Tile, HashSet<Gap>> fakeMap, PriorityQueue<Gap> copyOfHor, PriorityQueue<Gap> copyOfVert, PriorityQueue<Gap> copyOfAsc, PriorityQueue<Gap> copyOfDesc) {
        int startCoord[] = gap.start;
        //Except for the "leftover" gaps —
        if (!gap.hor) {
            Gap first = new Gap(gap.start, new int[]{startCoord[0] - 1, startCoord[1] - 1}, false, true);
            Gap second = new Gap(new int[]{startCoord[0] + gap.getSize(), startCoord[1] + gap.getSize()}, gap.end, false, true);
            descGapsInsert(first, second, copyOfDesc, fakeMap);
        } else {
            Gap first = new Gap(gap.start, new int[]{startCoord[0] + 1, startCoord[1] - 1}, true, true);
            Gap second = new Gap(new int[]{startCoord[0] - gap.getSize(), startCoord[1] + gap.getSize()}, gap.end, true, true);
            ascGapsInsert(first, second, copyOfAsc, fakeMap);
        }


        for (int x = 0; x < gap.getSize(); x++) {
            Tile curr;
            if (!gap.hor) {
                curr = grid[startCoord[0] + x][startCoord[1] + x];
            } else {
                curr = grid[startCoord[0] - x][startCoord[1] + x];
            }
            nullifyTile(curr, gap.hor, gap.diag, fakeMap, null, copyOfHor, copyOfVert, copyOfAsc, copyOfDesc);
        }
    }

    public static HashMap<Tile, HashSet<Gap>> duplicateMap(HashMap<Tile, HashSet<Gap>> map) {
        HashMap<Tile, HashSet<Gap>> ans = new HashMap<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (map.containsKey(grid[r][c])) {
                    ans.put(grid[r][c], copyHashset(map.get(grid[r][c])));
                }
            }
        }
        return ans;
    }

    public static HashSet<Gap> copyHashset(HashSet<Gap> gaps) {
        HashSet<Gap> dupl = new HashSet<>();
        for (Gap g : gaps) {
            dupl.add(g);
        }
        return dupl;
    }

    public static PriorityQueue<Gap>[] doubleCopy(PriorityQueue<Gap> heap) {
        PriorityQueue<Gap>[] answers = new PriorityQueue[2];
        answers[0] = new PriorityQueue<>(comparator);
        answers[1] = new PriorityQueue<>(comparator);
        while (!heap.isEmpty()) {
            Gap g = heap.poll();
            answers[0].add(g);
            answers[1].add(g);
        }
        return answers;
    }

    public static int sumStack(Stack<Integer> s) {
        Stack<Integer> store = new Stack<>();
        int ans = 0;
        while (!s.empty()) {
            int x = s.pop();
            ans += x;
            store.push(x);
        }
        while (!store.empty()) {
            s.push(store.pop());
        }
        return ans;
    }

    public static Stack<Integer> duplicateStack(Stack<Integer> s) {
        Stack<Integer> ans = new Stack<>();
        Stack<Integer> store = new Stack<>();
        while (!s.empty()) {
            store.push(s.pop());
        }
        while (!store.empty()) {
            int a = store.pop();
            ans.push(a);
            s.push(a);
        }
        return ans;
    }

    public static PriorityQueue<Gap> bestHeap(PriorityQueue<Gap> copyOfHor, PriorityQueue<Gap> copyOfVert, PriorityQueue<Gap> copyOfAsc, PriorityQueue<Gap> copyOfDesc) {
        if (copyOfHor.isEmpty() && copyOfVert.isEmpty() && copyOfAsc.isEmpty() && copyOfDesc.isEmpty()) {
            return null;
        }
        int vertSize = getHeapTop(copyOfVert);
        int horSize = getHeapTop(copyOfHor);
        int descSize = getHeapTop(copyOfDesc);
        int ascSize = getHeapTop(copyOfAsc);
        int max = Math.max(Math.max(vertSize, horSize), Math.max(descSize, ascSize));

        if (descSize == max) {
            return copyOfDesc;
        } else if (ascSize == max) {
            return copyOfAsc;
        } else if (vertSize == max) {
            return copyOfVert;
        } else {
            return copyOfHor;
        }
    }


    //Debugging methods

    public static HashSet<Gap> duplicateHashSet(HashSet<Gap> gaps) {
        HashSet<Gap> answer = new HashSet<>();
        for (Gap g : gaps) {
            answer.add(g);
        }
        return answer;
    }


    public static void printStack(Stack<Integer> dupl) {
        Stack<Integer> x = duplicateStack(dupl);
        while (!x.empty()) {
            System.out.println(x.pop());
        }
    }

    public static void testStack() {
        horHeap.add(new Gap(new int[]{0, 0}, new int[]{0, 4}, true, false));
        horHeap.add(new Gap(new int[]{1, 1}, new int[]{1, 4}, true, false));
        horHeap.add(new Gap(new int[]{2, 2}, new int[]{2, 4}, true, false));
        vertHeap.add(new Gap(new int[]{0, 0}, new int[]{4, 0}, false, false));
        vertHeap.add(new Gap(new int[]{1, 1}, new int[]{3, 1}, false, false));
        for (int i = 9; i <= 20; i++) {
            System.out.println("Going for " + i);
            stack = constructSeriesOfMoves(i);
            while (!stack.empty()) {
                System.out.println(stack.pop());
            }
            System.out.println();
        }
    }

}
