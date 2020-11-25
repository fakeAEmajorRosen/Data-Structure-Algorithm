import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various pattern matching algorithms.
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
public class PatternMatching {

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Pattern cannot be null.");
        } else if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Comparator cannot be null.");
        } else if (pattern.length() == 0) {
            return new int[0];
        }

        // Instantiation
        int m = pattern.length();
        int i = 0;
        int j = 1;
        int[] failureTable = new int[m];


        failureTable[0] = 0;


        // End case: when j reaches to the end
        while (j < m) {
            // If arr[i] == arr[j]
            //  Fill in i+1
            //  Increment both i and j

            // If arr[i] != arr[j]
            // 1) i == 0
            //      Fill in 0
            //      Increment j
            // 2) i != 0
            //      Set i to the value of f[i-1]

            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                failureTable[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    failureTable[j] = 0;
                    j++;
                } else {
                    i = failureTable[i - 1];
                }
            }
        }
        return failureTable;
    }


    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this
     * method. The amount to shift by upon a mismatch will depend on this table.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or of
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || (pattern.length() == 0)) {
            throw new IllegalArgumentException("Pattern cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        int m = pattern.length();
        int n = text.length();
        List<Integer> list = new ArrayList<Integer>();
        if (m > n) {
            return list;
        }
        int j = 0; // Pattern Index
        int i = 0; // Text Index

        int[] failureT = buildFailureTable(pattern, comparator);
        while (i - (j + 1) < (n - m)) {
            if (comparator.compare(text.charAt(i), pattern.charAt(j)) == 0) {
                // Increment
                i++;
                j++;

                if (j < m) {
                    // Continue searching
                    continue;
                } else {
                    // Pattern Found
                    list.add(i - j);
                }
            } else if (j == 0) {
                // Not found at [0]
                i++;
                continue;
            }
            // HW PDF: Treats match as mismatch
            j = failureT[j - 1];
        }
        return list;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x, where x is a particular
     * character in the pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to the index at which they last occurred in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        Map<Character, Integer> lastTable = new HashMap<Character, Integer>();
        for (int i = 0; i < pattern.length(); i++) {
            lastTable.put(pattern.charAt(i), i);
        }
        return lastTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Note: You may find the getOrDefault() method useful from Java's Map.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or of
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null || (pattern.length() == 0)) {
            throw new IllegalArgumentException("Pattern cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        int m = pattern.length();
        int n = text.length();
        List<Integer> list = new ArrayList<Integer>();

        // Not found case 0: pattern is longer than text
        if (m > n) {
            return list;
        }

        // build Last Occurrence Table
        Map<Character, Integer> table = buildLastTable(pattern);
        int i = 0;
        while (i <= (n - m)) {
            //J : Start from the end of the pattern
            int j = m - 1;

            // Case 1: i == j
            // Keep finding when j is >= 0
            while ((j >= 0) && (comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0)) {
                j = j - 1;
            }

            // Case 2: j reaches the front of the pattern  =>  FOUND
            if (j == -1) {
                //Pattern found
                list.add(i);
                i++;
                continue;
            } else {
                
                // Case 3: If j stops at the middle  =>  not found

                int shift = table.getOrDefault(text.charAt(i + j), -1);
                if (shift < j) {
                    //Shift to the last occurrence if necessary
                    i = i + j - shift;
                } else {
                    i++;
                }
            }
        }
        return list;
    }

    /*
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character.
     * The BASE to use been provided for you in the final variable on line 127.
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to find BASE^(m - 1).
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 113 hash
     * = b * 113 ^ 3 + u * 113 ^ 2 + n * 113 ^ 1 + n * 113 ^ 0 = 98 * 113 ^ 3 +
     * 117 * 113 ^ 2 + 110 * 113 ^ 1 + 110 * 113 ^ 0 = 142910419
     *
     * Another key step for this algorithm is that updating the hashcode from
     * one substring to the next one must be O(1). To update the hash:
     *
     * remove the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y =
     * (142910419 - 98 * 113 ^ 3) * 113 + 121 = 170236090
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^{m - 1} is for updating the hash.
     *
     * Do NOT use Math.pow() for this method.
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator the comparator to use when checking character equality
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or of
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || (pattern.length() == 0)) {
            throw new IllegalArgumentException("Pattern cannot be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        } else if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        int m = pattern.length();
        int n = text.length();
        List<Integer> list = new ArrayList<Integer>();

        // Not found case 0: pattern is longer than text
        if (m > n) {
            return list;
        }

        //Initial Hash generation:
        int textH = 0; // text hash
        int patternH = 0; // pattern hash
        int power = 1;
        for (int i = (m - 1); i >= 0; i--) {
            patternH = patternH + pattern.charAt(i) * power;
            textH = textH + text.charAt(i) * power;
            if (i > 0) {
                power = BASE * power;
            }
        }

        int i = 0; // text pointer

        while (i <= (n - m)) {
            if (patternH == textH) {
                int j = 0; // pattern pointer
                while (j < m && (comparator.compare(text
                        .charAt(i + j), pattern.charAt(j)) == 0)) {
                    j++;
                }

                if (j == m) {
                    list.add(i);
                }
                
            }

            if (i < (n - m)) {
                textH = (textH - text.charAt(i) * power) * BASE + text.charAt(i + m);
            }
            i++;
        }
        return list;
    }
}