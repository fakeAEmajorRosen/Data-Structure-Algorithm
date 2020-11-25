import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 *
 * Collaborators: I work on this assignment alone.
 *
 * Resources: I only use the course materials.
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("The array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("The comparator cannot be null");
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(arr[j], arr[j - 1]) >= 0) {
                    break;
                }
                swapHelper(arr, j, j - 1);
            }

        }
    }

    /**
     * Swap the element at index i and j in the array
     * @param array array to be operated
     * @param i index of the first element that will be swapped
     * @param j index of the second element that will be swapped
     * @param <T> data type to sort
     */
    private static <T> void swapHelper(T[] array, int i, int j) {
        if (i < 0 || j < 0 || i > array.length - 1 || j > array.length - 1) {
            throw new IllegalArgumentException("One of the indices is invalid");
        }
        T curr = array[i];
        array[i] = array[j];
        array[j] = curr;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        // Base case: if the length of the array is 1
        if (arr.length <= 1) {
            return;
        }

        // Find the mid point
        int len = arr.length;
        int mid = len / 2;

        // Copy the left and right to a new array respectively
        T[] left = copyHelper(arr, 0, mid - 1);
        T[] right = copyHelper(arr, mid, len - 1);

        // Recursively call the merge sort method on left and right
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // Main merge sort process
        // Initialize pointers for left and right array
        int i = 0;
        int j = 0;

        // Compare the elements in left and right array till one
        // of the pointer reaches the end of the array
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                // Take the left if it is equal
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }

        // If one of the pointers of left or right is not at the end,
        // put the rest of it in the merged array
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }

    }

    /**
     * Copy contents of the array to a new array
     * @param array array to be copied
     * @param start start index (inclusive)
     * @param end end index (inclusive)
     * @param <T> data type to sort
     * @return a new array with the selected elements copied
     */
    private static <T> T[] copyHelper(T[] array, int start, int end) {
        if (start < 0 || end < 0 || start >= array.length || end >= array.length) {
            throw new IllegalArgumentException("One of the indices is invalid");
        }
        T[] out = (T[]) (new Object[end - start + 1]);
        for (int k = 0; k < (end - start + 1); k++) {
            out[k] = array[k + start];
        }
        return out;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        // Making buckets(LinkedLists) for each indices of the array
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }


        // max: max number
        int max = 0;
        if (arr.length > 0) {
            max = arr[0];
        }

        // Find the max number in the array
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > Math.abs(max)) {
                max = arr[i];
            }
        }

        // Base: the number tha help us determine the largest digit
        int base = 1;

        // Loop through the number of digits
        while ((Math.abs(max) / base) > 0) {

            // Put numbers in buckets
            for (int j = 0; j < arr.length; j++) {
                int bucketNumber = (mod(arr[j] / base, 10) + 9);
                buckets[bucketNumber].addLast(arr[j]);
            }

            // Move the number from buckets back to the array
            int p = 0;
            for (LinkedList<Integer> bucket: buckets) {
                while (!(bucket.isEmpty())) {
                    Integer number = bucket.pop();
                    arr[p++] = number;
                }
            }

            // Update base
            base *= 10;
        }
    }

    /**
     * Helper method for modding the number in a special way.
     * @param a the number to be modded
     * @param b the modulo for modding
     * @return the special mod value
     */
    private static int mod(int a, int b) {
        if (a < 0) {
            int out = (-1) * (((-1) * a)) % b;
            return out;
        } else {
            return (a % b);
        }
    }
    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null.");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Rand cannot be null.");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is out of the range of this array.");
        }
        return kthSelectHelper(k, arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * Helper method for kthSelect()
     *
     * @param k the index to retrieve data from + 1 (due to
     *          0-indexing) if the array was sorted; the 'k' in "kth
     *          select"; e.g. if k == 1, return the smallest element
     *          in the array
     * @param arr the array that should be modified after the method
     *            is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param start the start index to select
     * @param end the end index to select
     * @param <T> data type to sort
     * @return the kth smallest element
     */
    private static <T> T kthSelectHelper(int k, T[] arr, Comparator<T> comparator,
                                         Random rand, int start, int end) {
        // base case: start == end
        if ((end - start) < 1) {
            return arr[start];
        }

        // Calculate the random pivot
        int pivotIdx = rand.nextInt(end + 1 - start) + start;
        T pivot = arr[pivotIdx];

        // Swap the pivot with the first element in the array
        swapHelper(arr, pivotIdx, start);

        // Initialize i and j pointer
        int i = start + 1;
        int j = end;

        // Main process:
        // When j and i is not crossed
        while ((j - i) >= 0) {

            // Find the i that is larger than the pivot
            while ((j - i) >= 0 && comparator.compare(arr[i], pivot) <= 0) {
                i++;
            }

            // Find the j that is smaller than the pivot
            while ((j - i) >= 0 && comparator.compare(arr[j], pivot) >= 0) {
                j--;
            }

            // If j and i is not crossed
            if ((j - i) >= 0) {
                // Swap i and j then increment i, decrement j
                swapHelper(arr, i, j);
                i++;
                j--;
            }
        }

        // After j and i is crossed
        // Swap start and j
        swapHelper(arr, start, j);

        // Determine k:
        // if kth (k - 1) is the same as index j: FOUND!
        if ((k - 1) == j) {
            return arr[j];
        }

        // if kth (k - 1) < index j: Find in the left array
        // if kth (k - 1) > index j: Find in the right array
        if ((k - 1) < j) {
            return kthSelectHelper(k, arr, comparator, rand, start, j - 1);
        } else {
            return kthSelectHelper(k, arr, comparator, rand, j + 1, end);
        }
    }
}
