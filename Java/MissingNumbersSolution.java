import java.util.Arrays;

public class MissingNumbersSolution {
    /**
     * Given an unsorted integer array with no duplicates, find the missing numbers
     * in the array.
     */
    private static void printMissingNumbers(int[] numbers, int count) {
        numbers.forEach(System.out::print);
        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.printf("Execution time: %,d nanosec%n%n", System.nanoTime() - start);
        numbers.forEach(System.out::print);
    }

    // Driver Code
    public static void main(String[] args) {

        // Given array arr[]
        int[] arr = { 6, 10, 7, 11, 13 };

        int n = arr.length;

        // Function call
        printMissingNumbers(arr, n);
    }
}
