
// https://codeshare.io/lon7PB
/* 
 * Given a list of positive whole numbers with no duplicates, 
 * print any 2 numbers of that list that sum up to a given target number
 * E.g. for the ist [3, 6, 9, 12, 15, 4, 2, 1, 20] and a target number 15, print "6 and 9"
 */
import java.util.ArrayList;
import java.util.HashSet;

class PrintPairsSolution {

    static void printPairs0(int[] arr, int n, int sum) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] + arr[j]) == sum) {
                    System.out.printf("%d and %d\n", arr[i], arr[j]);
                    break;
                }
            }
        }
    }

    static void printPairs1(int[] arr, int n, int sum) {
        ArrayList<Integer> buf = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (buf.contains(arr[i])) {
                System.out.printf("%d and %d%n", sum - arr[i], arr[i]);
            } else {
                buf.add(sum - arr[i]);
            }
        }
    }

    static void printPairs2(int arr[], int n, int sum) {
        HashSet<Integer> buffer = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (buffer.contains(arr[i])) {
                System.out.printf("%d and %d%n", sum - arr[i], arr[i]);
            } else {
                buffer.add(sum - arr[i]);
            }
        }
    }

    // Driver Code
    public static void main(String[] arg) {
        int[] arr = { 1, 5, 7, 2, 4, 6 };
        int n = arr.length;
        int sum = 6;
        long start = System.nanoTime();
        printPairs0(arr, n, sum);
        long finish = System.nanoTime();
        System.out.printf("excecution time for double loop: %,d ns%n", finish - start);
        start = System.nanoTime();
        printPairs1(arr, n, sum);
        finish = System.nanoTime();
        System.out.printf("excecution time for ArrayList: %,d ns%n", finish - start);
        start = System.nanoTime();
        printPairs2(arr, n, sum);
        finish = System.nanoTime();
        System.out.printf("excecution time for HashSet: %,d ns%n", finish - start);
    }
}
