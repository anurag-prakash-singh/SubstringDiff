import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by anurags on 8/22/15.
 */
public class Solution {
    private static <T> void printArrayElements(T[] values, int start, int len) {
        int end = start + len - 1;

        for (int i = start; i <= end && i < values.length; i++) {
            System.out.print(values[i]);
        }

        System.out.println();
    }

    private static <T> int[] maxRangeWithValuesLinearTime(T[] values,
                                                T check, int maxWithCheck) {
        int maxLength = 0;
        int maxLengthStart = 0;
        int checkMatches = 0;
        int tail = -1;
        int head = -1;

        while (head < values.length) {
            if (checkMatches <= maxWithCheck) {
                if (head - tail > maxLength) {
                    maxLengthStart = tail + 1;
                    maxLength = head - tail;
                }

                head++;

                if (head < values.length) {
                    if (values[head] == check) {
                        checkMatches++;
                    }
                }
            } else {
                tail++;

                if (tail < values.length) {
                    if (values[tail] == check) {
                        checkMatches--;
                    }
                }
            }
        }

        return new int[] {maxLengthStart, maxLength};
    }

    private static <T> int[] maxRangeWithValues(T[] values,
                                                T check, int maxWithCheck) {
        int maxLength = 0;
        int maxLengthStart = 0;
        int checkMatches = 0;

        for (int i = 0; i < values.length; i++) {
            checkMatches = 0;
            int j = i;

            for (; j < values.length; j++) {
                if (values[j] == check) {
                    checkMatches++;
                }

                if (checkMatches > maxWithCheck) {
                    break;
                }

                if (j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    maxLengthStart = i;
                }
            }
        }

        return new int[] {maxLengthStart, maxLength};
    }

    private static void testMaxRangeWithValues1() {
        Integer[] arr = new Integer[] {0, 1, 0, 1, 0, 1, 1, 1, 0};
        int[] result;

        result = maxRangeWithValues(arr, 0, 1);

        System.out.println("Result brute force: " + result[0] + ", " + result[1]);

        result = maxRangeWithValuesLinearTime(arr, 0, 1);

        System.out.println("Result efficient: " + result[0] + ", " + result[1]);
    }

    private static void testMaxRangeWithValues2() {
        Integer[] arr = new Integer[] {0};
        int[] result;

        result = maxRangeWithValues(arr, 0, 1);

        System.out.println("Result brute force: " + result[0] + ", " + result[1]);

        result = maxRangeWithValuesLinearTime(arr, 0, 1);

        System.out.println("Result efficient: " + result[0] + ", " + result[1]);
    }

    private static void testMaxRangeWithValues3() {
        Integer[][][] arr = new Integer[][][] {
                new Integer[][] {new Integer[] {1, 1, 1, 1, 1}, new Integer[] {2}},
                new Integer[][] {new Integer[] {0, 0, 0}, new Integer[] {0}},
                new Integer[][] {new Integer[] {1, 1, 1, 1}, new Integer[] {0}},
                new Integer[][] {new Integer[] {1, 1, 0, 1, 1}, new Integer[] {0}},
                new Integer[][] {new Integer[] {1, 1, 0, 1, 1}, new Integer[] {1}},
                new Integer[][] {new Integer[] {1, 1, 1, 1, 0}, new Integer[] {1}},
                new Integer[][] {new Integer[] {1, 1, 1, 1, 0}, new Integer[] {0}},
                new Integer[][] {new Integer[] {0, 1, 1, 0, 0, 1}, new Integer[] {2}},
                new Integer[][] {new Integer[] {0, 1, 1, 0, 0, 1}, new Integer[] {1}},
        };
        int[] resultBrute, resultEfficient;
        boolean verbose = false;

        for (int i = 0; i < arr.length; i++) {
            resultBrute = maxRangeWithValues(arr[i][0], 0, arr[i][1][0]);
            resultEfficient = maxRangeWithValuesLinearTime(arr[i][0], 0, arr[i][1][0]);

            if (verbose) {
                System.out.println("Result brute force: " + resultBrute[0] + ", " + resultBrute[1]);
                System.out.println("Result efficient: " + resultEfficient[0] + ", " + resultEfficient[1]);
            }

            if (resultBrute[0] != resultEfficient[0] || resultBrute[1] != resultEfficient[1]) {
                System.out.println("=== Test " + (i + 1) + " FAILED ===");
            } else {
                System.out.println("=== Test " + (i + 1) + " PASSED ===");
            }
        }
    }

    private static int getLongestMaxDiffLength(String s1, String s2, int l) {
//        boolean[][] matches = new boolean[s1.length()][s1.length()];
        int maxLength = 0;

//        for (int i = 0; i < matches.length; i++) {
//            for (int j = 0; j < matches.length; j++) {
//                if (s1.charAt(i) == s2.charAt(j)) {
//                    matches[i][j] = true;
//                } else {
//                    matches[i][j] = false;
//                }
//            }
//        }

        Boolean[] matchArr = new Boolean[s1.length()];

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                matchArr[i] = true;
            } else {
                matchArr[i] = false;
            }
        }

        int[] results = maxRangeWithValuesLinearTime(matchArr, false, l);

        maxLength = results[1];

        for (int i = 1; i < s1.length(); i++) {
            matchArr = new Boolean[s1.length() - i];
            int startS1 = i;
            int startS2 = 0;

            for (int j = 0; j < matchArr.length; j++) {
                if (s1.charAt(startS1 + j) == s2.charAt(startS2 + j)) {
                    matchArr[j] = true;
                } else {
                    matchArr[j] = false;
                }
            }

            results = maxRangeWithValuesLinearTime(matchArr, false, l);

            if (results[1] > maxLength) {
                maxLength = results[1];
            }

            startS1 = 0;
            startS2 = i;

            for (int j = 0; j < matchArr.length; j++) {
                if (s1.charAt(startS1 + j) == s2.charAt(startS2 + j)) {
                    matchArr[j] = true;
                } else {
                    matchArr[j] = false;
                }
            }

            results = maxRangeWithValuesLinearTime(matchArr, false, l);

            if (results[1] > maxLength) {
                maxLength = results[1];
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
//        testMaxRangeWithValues1();
//        testMaxRangeWithValues2();
//        testMaxRangeWithValues3();
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                String[] components = line.split(" ");
                int l = Integer.parseInt(components[0]);
                String s1 = components[1];
                String s2 = components[2];

                System.out.println(getLongestMaxDiffLength(s1, s2, l));
            }
        } catch (Exception exception) {
            // Ignore
        }
    }
}
