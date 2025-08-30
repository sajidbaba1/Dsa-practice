import java.util.Arrays;

/**
 * <h2>242. Valid Anagram</h2>
 * <p>
 * Given two strings {@code s} and {@code t}, return {@code true} if {@code t} is an anagram of {@code s}, and {@code false} otherwise.
 * </p>
 * <p>
 * An <strong>Anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * </p>
 *
 * <h3>Example 1:</h3>
 * <p>
 * <strong>Input:</strong> s = "anagram", t = "nagaram"<br>
 * <strong>Output:</strong> true
 * </p>
 *
 * <h3>Example 2:</h3>
 * <p>
 * <strong>Input:</strong> s = "rat", t = "car"<br>
 * <strong>Output:</strong> false
 * </p>
 *
 * <hr>
 *
 * <h3>Explanation of Approaches</h3>
 *
 * <h4>1. Brute-force Approach: Sorting</h4>
 * <p>
 * The most straightforward way to determine if two strings are anagrams is to see if they are identical when their characters are sorted.
 * If {@code t} is an anagram of {@code s}, then sorting both strings will result in two identical strings.
 * </p>
 * <p>
 * <strong>Steps:</strong>
 * <ol>
 *     <li>Check if the lengths of {@code s} and {@code t} are different. If they are, they cannot be anagrams, so return {@code false}.</li>
 *     <li>Convert both strings to character arrays.</li>
 *     <li>Sort both character arrays.</li>
 *     <li>Compare the sorted arrays. If they are equal, the strings are anagrams.</li>
 * </ol>
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n log n), where n is the length of the strings. The dominant operation is sorting the character arrays.
 * </p>
 * <p>
 * <strong>Space Complexity:</strong> O(n). In Java, {@code toCharArray()} creates a new character array of size n.
 * </p>
 *
 * <h4>2. Optimized Approach: Frequency Counter (Using an Array)</h4>
 * <p>
 * Since the problem statement mentions that the strings will contain only lowercase English letters, we can use a simple array of size 26 as a frequency counter instead of a more general (and slightly slower) hash map. This is faster and more space-efficient.
 * </p>
 * <p>
 * <strong>This is the implemented solution below.</strong>
 * </p>
 * <p>
 * <strong>Time Complexity:</strong> O(n), as we iterate through the strings once.
 * </p>
 * <p>
 * <strong>Space Complexity:</strong> O(1), because the size of the array is constant (26), regardless of the input string length.
 * </p>
 */
public class ValidAnagram {

    /**
     * Determines if string t is an anagram of string s using a frequency counter array.
     *
     * @param s The first string.
     * @param t The second string.
     * @return {@code true} if t is an anagram of s, {@code false} otherwise.
     */
    public boolean isAnagram(String s, String t) {
        // Step 1: Check for length mismatch. If lengths are different, they can't be anagrams.
        // This is a crucial optimization to fail fast.
        if (s.length() != t.length()) {
            return false;
        }

        // Step 2: Create a frequency counter.
        // We use an array of size 26 because the problem states the strings contain only
        // lowercase English letters ('a' through 'z').
        // Each index in the array corresponds to a letter. 0 -> 'a', 1 -> 'b', and so on.
        int[] charCounts = new int[26];

        // Step 3: Iterate through the first string `s` and increment the count for each character.
        for (int i = 0; i < s.length(); i++) {
            // `s.charAt(i) - 'a'` gives us the index (0-25) corresponding to the character.
            // For example, if s.charAt(i) is 'c', then 'c' - 'a' = 2.
            // We then increment the value at that index.
            charCounts[s.charAt(i) - 'a']++;
        }

        // Step 4: Iterate through the second string `t` and decrement the count for each character.
        for (int i = 0; i < t.length(); i++) {
            // We do the same calculation to find the index for the character in `t`.
            int index = t.charAt(i) - 'a';
            // We decrement the count at that index.
            charCounts[index]--;
            // After decrementing, if the count becomes negative, it means `t` has more of this
            // character than `s`, so they can't be anagrams.
            if (charCounts[index] < 0) {
                return false;
            }
        }

        // Step 5: If we've gone through the entire string `t` and never found a negative count,
        // it means the character frequencies are identical. Because we already checked that the
        // lengths are the same, we don't need to check if all counts are zero.
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();

        // Test Case 1: Basic anagram
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Test Case 1: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Expected: true, Got: " + solution.isAnagram(s1, t1));
        System.out.println();

        // Test Case 2: Not an anagram
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Test Case 2: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Expected: false, Got: " + solution.isAnagram(s2, t2));
        System.out.println();

        // Test Case 3: Different lengths
        String s3 = "a";
        String t3 = "ab";
        System.out.println("Test Case 3: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Expected: false, Got: " + solution.isAnagram(s3, t3));
        System.out.println();

        // Test Case 4: Anagram with repeated characters
        String s4 = "listen";
        String t4 = "silent";
        System.out.println("Test Case 4: s = \"" + s4 + "\", t = \"" + t4 + "\"");
        System.out.println("Expected: true, Got: " + solution.isAnagram(s4, t4));
        System.out.println();

        // Test Case 5: Same length, same characters, different frequencies
        String s5 = "aab";
        String t5 = "abb";
        System.out.println("Test Case 5: s = \"" + s5 + "\", t = \"" + t5 + "\"");
        System.out.println("Expected: false, Got: " + solution.isAnagram(s5, t5));
        System.out.println();

        // Test Case 6: Empty strings
        String s6 = "";
        String t6 = "";
        System.out.println("Test Case 6: s = \"" + s6 + "\", t = \"" + t6 + "\"");
        System.out.println("Expected: true, Got: " + solution.isAnagram(s6, t6));
        System.out.println();
    }
}
