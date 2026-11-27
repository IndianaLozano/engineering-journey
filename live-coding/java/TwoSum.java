import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 *
 * Given an unsorted array and a target, return the indices of the two numbers that add up to it.
 * Exactly one solution exists and the same element can't be used twice.
 *
 * Run:  java TwoSum.java
 */
public class TwoSum {

    /**
     * Brute force: try every pair (p1, p2) with p2 > p1.
     * p1 stops at length - 2 because p2 needs at least one element after it.
     * Time O(n^2), space O(1).
     */
    static int[] twoSumBruteForce(int[] nums, int target) {
        for (int p1 = 0; p1 < nums.length - 1; p1++) {
            for (int p2 = p1 + 1; p2 < nums.length; p2++) {
                if (nums[p1] + nums[p2] == target) {
                    return new int[]{p1, p2};
                }
            }
        }
        return new int[0];
    }

    /**
     * Optimized: one pass with a HashMap of value -> index.
     * For each number, look for its complement (target - number) among the numbers already seen.
     * Check BEFORE storing the current number, so an element can't pair with itself.
     * Time O(n), space O(n).
     */
    static int[] twoSum(int[] nums, int target) {
        // Presized so the map doesn't rehash while growing.
        Map<Integer, Integer> seen = new HashMap<>(nums.length * 2);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // One lookup instead of containsKey + get.
            Integer j = seen.get(complement);
            if (j != null) {
                return new int[]{j, i};
            }
            seen.put(nums[i], i);
        }
        return new int[0]; // no pair found
    }

    // ---- tiny test harness ----

    static int failures = 0;

    static void check(String name, int[] actual, int... expected) {
        boolean ok = Arrays.equals(actual, expected);
        if (!ok) failures++;
        System.out.println((ok ? "PASS " : "FAIL ") + name + " -> " + Arrays.toString(actual));
    }

    public static void main(String[] args) {
        check("example 1", twoSum(new int[]{2, 7, 11, 15}, 9), 0, 1);
        check("example 2", twoSum(new int[]{3, 2, 4}, 6), 1, 2);
        check("duplicates", twoSum(new int[]{3, 3}, 6), 0, 1);
        check("negatives", twoSum(new int[]{-1, -2, -3, -4, -5}, -8), 2, 4);
        check("no solution", twoSum(new int[]{1, 2}, 9));
        check("brute force agrees", twoSumBruteForce(new int[]{2, 7, 11, 15}, 9), 0, 1);
        System.out.println(failures == 0 ? "All tests passed" : failures + " test(s) failed");
        if (failures > 0) System.exit(1);
    }
}
