package com.boot.specifications.alg;

import java.util.Comparator;
import java.util.Random;

public class gapComparator implements Comparator<Gap> {
    public static int distribution = 0;
    public static int times = 0;
    public static Random rand;
    public static int modFactor;


    public gapComparator(Random r) {
        rand = r;
        modFactor = rand.nextInt(100000) + 50;
    }

    @Override
    public int compare(Gap o1, Gap o2) {
        int scoreDiff = o2.getSize() - o1.getSize();
        if (scoreDiff != 0) {
            return scoreDiff;
        }
        distribution++;
        int firstScore = o1.hashCode();
        int secondScore = o2.hashCode();
        int ans = (secondScore % modFactor) - (firstScore % modFactor);
        if ((ans > 0 && secondScore - firstScore > 0) || (ans < 0 && secondScore - firstScore < 0)) {
            times++;
        }
        if (ans == 0) {
            int expandedAns = secondScore - firstScore;
            if (expandedAns == 0) {
                System.out.println("your so called math skills are garbo");
            }
            return expandedAns;
        } else {
            return ans;
        }
    }
}

