import java.util.Random;

public class SearchComparison {

    public static long[] linearSearch(int[] arr, int target) {
        long operations = 0;
        for (int i = 0; i < arr.length; i++) {
            operations++;
            if (arr[i] == target) {
                return new long[]{i, operations};
            }
        }
        return new long[]{-1, operations};
    }

    public static long[] binarySearch(int[] arr, int target) {
        long operations = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            operations++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return new long[]{mid, operations};
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new long[]{-1, operations};
    }

    public static void main(String[] args) {
        int[] inputSizes = {1000, 5000, 10000, 50000, 100000};
        Random random = new Random();

        System.out.println("Running experiments...\n");

        for (int size : inputSizes) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = i;
            }

            int target = arr[random.nextInt(size)];

            long startTimeLinear = System.nanoTime();
            long[] linearResult = linearSearch(arr, target);
            long endTimeLinear = System.nanoTime();
            double linearTimeSec = (endTimeLinear - startTimeLinear) / 1_000_000_000.0;

            long startTimeBinary = System.nanoTime();
            long[] binaryResult = binarySearch(arr, target);
            long endTimeBinary = System.nanoTime();
            double binaryTimeSec = (endTimeBinary - startTimeBinary) / 1_000_000_000.0;

            System.out.printf("Size: %d | Target: %d%n", size, target);
            System.out.printf("  Linear: %.6fs, %d ops%n", linearTimeSec, linearResult[1]);
            System.out.printf("  Binary: %.6fs, %d ops%n%n", binaryTimeSec, binaryResult[1]);
        }
    }
}