package com.boot.specifications.alg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Gap implements Comparable<Gap> {
    int[] start;
    int[] end;
    boolean hor;
    boolean diag;
    HashMap<Gap, Integer> map = new HashMap<>();

    //A collection of adjacent Tiles, represents a potential place to put a word
    public Gap(int[] s, int[] e, boolean h, boolean d) {
        hor = h;
        diag = d;
        start = s;
        end = e;
    }

    public int getSize() {
        if (hor) {
            return end[1] - start[1] + 1;
        } else {
            return end[0] - start[0] + 1;
        }
    }

    @Override
    public int hashCode() {
        int answer = 0;
        int factor = Math.max(Wordsearch.height, Wordsearch.width) + 1;
        for (int i: start) {
            answer *= factor;
            answer += i + 1;
        }
        for (int i: end) {
            answer *= factor;
            answer += i + 1;
        }
        return answer * 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Gap newO = (Gap) o;
        return newO.start[0] == this.start[0] && newO.start[1] == this.start[1]
                && newO.end[0] == this.end[0] && newO.end[1] == this.end[1];
    }

    @Override
    public String toString() {
        String ans = "Gap (";
        String sym = ",";
        for (int x : start) {
            ans += "" + x + sym;
            sym = "";
        }
        ans += ") --> (";
        sym = ",";
        for (int x : end) {
            ans += "" + x + sym;
            sym = "";
        }
        return ans + ") of size " + getSize();
    }

    @Override
    public int compareTo(Gap o) {
        int ans = o.getSize() - this.getSize();
        if (ans == 0) {
            return 50 - Wordsearch.rand.nextInt(100);
        } else {
            return ans;
        }
    }
}

/*
True and True (hor and diag)
   /
  /
 /
/

False and True (!hor and diag)

\
 \
  \
   \
 */


