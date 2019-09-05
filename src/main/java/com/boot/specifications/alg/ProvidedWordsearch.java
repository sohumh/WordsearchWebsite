package com.boot.specifications.alg;

import java.util.*;

public class ProvidedWordsearch extends Wordsearch {
    static ArrayList<Integer> lstIndexes;
    static HashMap<Character, LinkedList<String>> letterMap;

    public ProvidedWordsearch(int r, int c, String hiddenMessage, int seed, LinkedList<String> listOfWords, int tries) {
        super(r, c, hiddenMessage, seed, tries);
        letterMap = new HashMap<>();
        words = duplicate(listOfWords);
        lstIndexes = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            lstIndexes.add(i);
        }
        //Arbitrarily create it
        spacesNeededToFill = 100000;
        seen = duplicate(words);
    }
    
    private static LinkedList<String> duplicate(LinkedList<String> lst) {
        LinkedList<String> answer = new LinkedList<>();
        for (String w: lst) {
            answer.add(w);
        }
        return answer;
    }

    public static boolean run(boolean show) { //Pass in the args needed for the Wordsearch constructor
        boolean ans = providedWordsAlg();
        Wordsearch.showGrid(show);
        return ans;
    }

    //We are using this word, so we must remove it from the overall list and from all the hashsets
    public static void removeWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            letterMap.get(curr).remove(word);
        }
        words.remove(word);
    }

    public static boolean providedWordsAlg() {
        sort();
        setUpDataStructures();
        while (!words.isEmpty() && !failure) {
            wordsProvidedRun();
        }
        return repeatsNeeded == 0 && !failure;
    }


    public static void wordsProvidedRun() {
        int vertSize = getHeapTop(vertHeap);
        int horSize = getHeapTop(horHeap);
        int descSize = getHeapTop(descHeap);
        int ascSize = getHeapTop(ascHeap);
        int max = Math.max(Math.max(vertSize, horSize), Math.max(descSize, ascSize));
        int needed = words.peekLast().length();
        if (max < needed) {
            failure = true;
            return;
        }
        if (tries <= 5) {
            onlyDiagRun(vertSize, horSize, descSize, ascSize, needed);
        } else if (tries <= 10) {
            balancedRun(vertSize, horSize, descSize, ascSize, needed);
        } else {
            horAndVertRun(vertSize, horSize, descSize, ascSize, needed);
        }
    }

    private static void horAndVertRun(int vertSize, int horSize, int descSize, int ascSize, int needed) {
        boolean x = rand.nextBoolean();
        for (int i = 0; i < 2; i++) {
            if (horSize >= needed && x) {
                horHeapRemoval();
                return;
            } else if (vertSize >= needed && !x){
                vertHeapRemoval();
                return;
            }
            x = !x;
        }
        for (int i = 0; i < 2; i++) {
            if (ascSize >= needed && x) {
                ascHeapRemoval();
                return;
            } else if (descSize >= needed && !x){
                descHeapRemoval();
                return;
            }
            x = !x;
        }
        failure = true;
    }

    private static void balancedRun(int vertSize, int horSize, int descSize, int ascSize, int needed) {
        //TODO: Make different game modes, i.e., we want to look at the game mode that is the most biased towards diags, then towards vert, then fair
        //Todo: we only want to play a different version if our secret message is shorter relative to the minimum

        Collections.shuffle(lstIndexes, rand);
        for (Integer index : lstIndexes) {
            switch (index) {
                case 1:
                    if (descSize >= needed) {
                        descHeapRemoval();
                        timesDesc++;
                        lstIndexes.add(2);
                        lstIndexes.add(3);
                        lstIndexes.add(4);
                        return;
                    }
                case 2:
                    if (ascSize >= needed) {
                        ascHeapRemoval();
                        timesAsc++;
                        lstIndexes.add(1);
                        lstIndexes.add(3);
                        lstIndexes.add(4);
                        return;
                    }
                case 3:
                    if (vertSize >= needed) {
                        vertHeapRemoval();
                        timesVert++;
                        lstIndexes.add(2);
                        lstIndexes.add(1);
                        lstIndexes.add(4);
                        return;
                    }
                case 4:
                    if (horSize >= needed) {
                        horHeapRemoval();
                        timesHor++;
                        lstIndexes.add(2);
                        lstIndexes.add(3);
                        lstIndexes.add(1);
                        return;
                    }
            }
        }
    }

    private static void onlyDiagRun(int vertSize, int horSize, int descSize, int ascSize, int needed) {
        boolean x = rand.nextBoolean();
        for (int i = 0; i < 2; i++) {
            if (ascSize >= needed && x) {
                ascHeapRemoval();
                return;
            } else if (descSize >= needed && !x){
                descHeapRemoval();
                return;
            }
            x = !x;
        }
        for (int i = 0; i < 2; i++) {
            if (horSize >= needed && x) {
                horHeapRemoval();
                return;
            } else if (vertSize >= needed && !x){
                vertHeapRemoval();
                return;
            }
            x = !x;
        }
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
        HashMap<Tile, TreeSet<Gap>> allGaps = new HashMap<>();

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
        addressFasterCollisions(allGaps, word);
    }

    public static void addressFasterCollisions(HashMap<Tile, TreeSet<Gap>> allGaps, String word) {
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

    public static int addToLetterMap(LinkedList<String> words) {
        int count = 0;
        for (String word : words) {
            HashSet<Character> seen = new HashSet<>();
            for (int i = 0; i < word.length(); i++) {
                char a = word.charAt(i);
                count++;
                if (!letterMap.containsKey(a)) {
                    letterMap.put(a, new LinkedList<>());
                }
                if (seen.contains(a)) {
                    continue;
                }
                if (rand.nextBoolean()) {
                    letterMap.get(a).addFirst(word);
                } else {
                    letterMap.get(a).addFirst(word);
                }
                seen.add(a);
            }
        }
        return count;
    }

    public static void setUpDataStructures() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                Tile newTile = new Tile(r, c, filler, letterMap);
                map.put(newTile, new HashSet<>());
                grid[r][c] = newTile;
            }
        }
        addToHorHeap();
        addToVertHeap();
        addToAscHeap();
        addToDescHeap();
    }

    public static boolean tryToInsertCollisions(Gap gap, Tile tile, int spot) {
        LinkedList<String> dupl = duplicateLinkedList(letterMap.get(tile.character));
        boolean inserted = false;
        for (String word : dupl) {
            if (checkSpacesNeededToFill()) {
                break;
            }
            if (word.length() > gap.getSize() || validityCheck(gap, tile) == -1 || !words.contains(word)) {
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
        return inserted;
    }

    //HEAP REPEATED ACTIONS
    public static void ascHeapRemoval() {
        diagHeapRemovalSteps(false, ascHeap);
    }

    public static void descHeapRemoval() {
        diagHeapRemovalSteps(true, descHeap);
    }

    public static void diagHeapRemovalSteps(boolean desc, PriorityQueue<Gap> heap) {
        Gap gap = heap.poll();
        //System.out.println(gap);
        int size;
        String word;
        int change;
        word = ProvidedWordsearch.words.getLast();
        //System.out.println(word);
        removeWord(word);
        size = word.length();
        if (gap == null || size > gap.getSize()) {
            failure = true;
            return;
        }
        change = 0;
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

        HashMap<Tile, TreeSet<Gap>> allGaps = new HashMap<>();
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
        if (desc) {
            addToLocationsMap(word, startCoord, new int[]{startCoord[0] + size - 1, startCoord[1] + size - 1});
        } else {
            addToLocationsMap(word, startCoord, new int[]{startCoord[0] - size + 1, startCoord[1] + size - 1});
        }
        addressFasterCollisions(allGaps, word);
    }

    public static void horHeapRemoval() {
        commonHeapRemovalSteps(horHeap,1);
    }

    public static void vertHeapRemoval() {
        commonHeapRemovalSteps(vertHeap,0);
    }

    public static void commonHeapRemovalSteps(PriorityQueue<Gap> heap, int index) {
        Gap gap = heap.poll();
        //System.out.println(gap);

        boolean forwards = rand.nextBoolean();
        String word;
        int size;
        int insertCoord;
        word = words.getLast();
        //System.out.println(word);
        removeWord(word);
        size = word.length();
        if (gap == null || size > gap.getSize()) {
            failure = true;
            return;
        }
        insertCoord = gap.start[index] + rand.nextInt(gap.getSize() - size + 1);
        //dictionary.getWord(size, nextCategory(), forwards); //flips word if direction is backwards
        //Removal process: We only care about reinserting gaps in the opposite direction of the gap we remove
        //Except for the "leftover" gaps —
        if (gap.hor) {
            addHorLeftoverWordGaps(gap, insertCoord, size);
        } else {
            addVertLeftoverWordGaps(gap, insertCoord, size);
        }

        if (gap.hor) {
            addToLocationsMap(word, new int[]{gap.start[0], insertCoord}, new int[]{gap.start[0], insertCoord + size - 1});
        } else {
            addToLocationsMap(word, new int[]{insertCoord, gap.start[1]}, new int[]{insertCoord + size - 1, gap.start[1]});
        }
        HashMap<Tile, TreeSet<Gap>> allGaps = new HashMap<>();
        for (int x = 0; x < size; x++) {
            Tile curr;
            if (gap.hor) {
                curr = grid[gap.start[0]][insertCoord + x];
            } else {
                curr = grid[insertCoord + x][gap.start[1]];
            }
            curr.character = word.charAt(x);
            try {
                nullifyTile(curr, gap.hor, gap.diag, map, allGaps, horHeap, vertHeap, ascHeap, descHeap);
            } catch (NullPointerException n) {
                failure = true;
                return;
            }
        }
        addressFasterCollisions(allGaps, word);
    }

    //WORD ENTER METHODS

    public static void sort() {
        sortWordsSemiRandomly();
        int count = addToLetterMap(words);
        determineGridSize(count + message.length());
        System.out.println("////");
        System.out.println(message.length());
        System.out.println(width);
        System.out.println(height);
        System.out.println(count);
        System.out.println("////");

        repeatsNeeded = message.length() + count - (width * height);
    }

    public static void sortWordsSemiRandomly() {
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, LinkedList<String>> map = new HashMap<>();
        while (!words.isEmpty()) {
            String word = words.removeFirst();
            int size = word.length();
            set.add(size);
            if (!map.containsKey(size)) {
                map.put(size, new LinkedList<>());
            }
            map.get(size).add(word);
        }
        for (int size : set) {
            LinkedList<String> currList = map.get(size);
            while (!currList.isEmpty()) {
                String word = currList.remove(rand.nextInt(currList.size()));
                if (rand.nextBoolean()) {
                    words.addLast(word);
                } else {
                    words.addLast(Word.reverse(word));
                }
            }
        }
    }

    public static void determineGridSize(int num) {
        System.out.println(words.size());
        for (String w: words) {
            System.out.println(w);
        }
        int count = words.peekLast().length();

        for (int i = Math.max(4, count / 2); i < count; i++) {
            if (tryWidthAndHeightOf(i, count, num)) {
                return;
            }
        }
        while (true) {
            boolean ans = tryWidthAndHeightOf(count, count, num)
                    || tryWidthAndHeightOf(count - 1, count + 2, num)
                    || tryWidthAndHeightOf(count, count + 1, num)
                    || tryWidthAndHeightOf(count - 1, count + 3, num)
                    || tryWidthAndHeightOf(count, count + 2, num);
            if (ans) {
                return;
            }
            count += 1;
        }
    }

    public static boolean tryWidthAndHeightOf(int w, int h, int num) {
        if (w * h > num) {
            grid = new Tile[height][width];
            finalAnswer = new char[height][width];
            return true;
        }
        width = w;
        height = h;
        return false;
    }
}
