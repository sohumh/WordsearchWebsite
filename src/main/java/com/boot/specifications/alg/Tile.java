package com.boot.specifications.alg;

import java.util.HashMap;
import java.util.LinkedList;

public class Tile implements Comparable<Tile> {
    int row;
    int col;
    char character;
    HashMap<Character, LinkedList<String>> p;

    public Tile(int row, int col, char c,  HashMap<Character, LinkedList<String>> p) {
        this.row = row;
        this.col = col;
        character = c;
        this.p = p;
    }

    public int score() {
        int ans;
        try {
            ans = p.get(this.character).peekFirst().length();
        } catch (NullPointerException n) {
            ans = 0;
        }
        return ans;
    }

    @Override
    public String toString(){
        return "Tile, row: " + row + " col: " + col;
    }

    @Override
    public int compareTo(Tile o) {
       int ans = o.score() - this.score();
       if (ans == 0) {
           return 50 - Wordsearch.rand.nextInt(100);
       } else {
           return ans;
       }
    }
}
