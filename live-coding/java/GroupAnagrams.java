import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * LeetCode 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Group the words that are anagrams of each other (any order of groups).
 * LeetCode constraint: lowercase English letters only.
 *
 * Run:  java GroupAnagrams.java
 */
public class GroupAnagrams {

    /**
     * Version 1: the key is the word with its letters sorted ("eat" -> "aet").
     * All anagrams sort to the same key, so the map holds key -> list of words.
     * Time O(n * k log k), space O(n * k), n words of length up to k.
     */
    static List<List<String>> groupAnagramsSorted(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars); // sorts in place, returns nothing
            String key = new String(chars);

            // computeIfAbsent = "if the key isn't there, put a new list", in one call
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    /**
     * Version 2 (optimized): the key is the letter COUNT of the word, so there is no sorting.
     * "eat" -> "1#0#0#0#1#...#1#..." (count of 'a', 'b', ... 'z').
     * Time O(n * k), space O(n * k). Only valid for a-z, as in the problem.
     */
    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] counts = new int[26];
            for (int i = 0; i < word.length(); i++) {
                counts[word.charAt(i) - 'a']++;
            }
            // The separator avoids collisions such as counts 1,11 vs 11,1.
            StringBuilder key = new StringBuilder();
            for (int c : counts) {
                key.append(c).append('#');
            }
            groups.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    // ---- tiny test harness (groups and words are compared without caring about order) ----

    static int failures = 0;

    static String normalize(List<List<String>> groups) {
        TreeSet<String> out = new TreeSet<>();
        for (List<String> g : groups) {
            List<String> copy = new ArrayList<>(g);
            copy.sort(null);
            out.add(copy.toString());
        }
        return out.toString();
    }

    static void check(String name, List<List<String>> actual, String expectedNormalized) {
        String got = normalize(actual);
        boolean ok = got.equals(expectedNormalized);
        if (!ok) failures++;
        System.out.println((ok ? "PASS " : "FAIL ") + name + " -> " + actual);
    }

    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String expected = "[[ate, eat, tea], [bat], [nat, tan]]";

        check("sorted key", groupAnagramsSorted(words), expected);
        check("count key", groupAnagrams(words), expected);
        check("empty string", groupAnagrams(new String[]{""}), "[[]]" /* one group holding one empty string */);
        check("single word", groupAnagrams(new String[]{"a"}), "[[a]]");
        System.out.println(failures == 0 ? "All tests passed" : failures + " test(s) failed");
        if (failures > 0) System.exit(1);
    }
}
