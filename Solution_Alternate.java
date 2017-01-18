
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution_Alternate {

    public static int maxLength(int[] a, int k) {

        int start = 0;
        int sum = 0;
        int max = 0;

        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];

            while (sum > k) {
                max = Math.max(max, i - start);
                sum = sum - a[start];
                start += 1;
            }

            if (sum <= k) {
                max = Math.max(max, i + 1 - start);
            }
        }
        return max;
    }


    public static void main(String[] args) {
    }
}
