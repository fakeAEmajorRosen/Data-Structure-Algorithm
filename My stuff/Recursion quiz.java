// Recursive summing an array
public int rsum(int[] arr) {
    return rsum(arr, 0);
}

private int rsum(int[] arr, int n) {
    if (n >= arr.length) {
        return 0;
    } else {
        return arr[n] + rsum(arr, n + 1);
    }
}

///////////////////////////////////////////////////
// Recursive binary search
int rBinarySearch(int[] arr) {
    return rbinarySearch(int[] arr, int start, int end, int target);
}

int rBinarySearch(int[] arr, int start, int end, int target) {
    if (end < start) { // base case
        return -1;
    }
    int mid = (start + end) / 2;
    if (target == arr[mid]) {
        return mid; // base case
    } else if (target < arr[mid]) {
        return rBinarySearch(arr, start, mid - 1, target); // recursive case
    } else {
        return rBinarySearch(arr, mid + 1, end, target); // recursive case
    }
}

/////////////////////////////////////////////////////////////////////////////////
// Recursive Reversing an array
//EX: array of length 5: 0 1 2 3 4
// (0,4) -> 4 1 2 3 0
// (1,3) -> 4 3 2 1 0

public void reverseListRecursively(ArrayList<String> list) {
    reverseListRecursively(list, 0, list.size() - 1);
}

public void reverseListRecursively(ArrayList<String> list, int left, int right) {
    if (left < right) {
        // Swap
        String temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
        // Recurse
        reverseListRecursively(list, left + 1, right - 1);
    }
}