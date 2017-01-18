
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int maxLength(int[] a, int k) {

        int[][] interim = new int[a.length][];

        for (int i = 0; i < a.length; i++) {
            int[] toadd = new int[1];
            toadd[0] = a[i];
            interim[i] = toadd;
        }

        for (int j = 1; j < a.length; j++) {
            ArrayList<int[]> toadd = new ArrayList<>();
            for (int z = 0; z < interim.length; z++) {
                int len = interim[z].length;
                if (check(interim[z], a[j], k) && (a[j - 1] == interim[z][len - 1])) {
                    int[] ne = new int[interim[z].length + 1];
                    for (int q = 0; q < interim[z].length; q++) {
                        ne[q] = interim[z][q];
                    }
                    ne[ne.length - 1] = a[j];
                    toadd.add(ne);
                }
            }

            int[][] adder = new int[interim.length + toadd.size()][];
            System.arraycopy(interim, 0, adder, 0, interim.length);
            for (int i = interim.length; i < toadd.size() + interim.length; i++) {
                adder[i] = toadd.get(i - interim.length);
            }
            interim = adder;
        }

        int max = 0;

        for (int[] q: interim) {
            for (int i = 0; i < q.length; i ++) {
                System.out.println(q[i]);
            }
            System.out.println("***");
        }

        for (int[] j : interim) {
            if (j.length > max && j[0] <= k) {
                max = j.length;
            }
        }
        return max;
    }

    public static boolean check(int[] totest, int add, int k) {
        int sum = 0;

        for (int i = 0; i < totest.length; i++) {
            sum += totest[i];
            if (sum > k) {
                return false;
            }
        }
        sum += add;

        if (sum > k) {
            return false;
        } else return true;
    }


    public static void main(String[] args) {
    }
}
