// LeetCode 1. Two Sum: https://leetcode.com/problems/two-sum/
// Pattern: HashMap (value -> index), look up the complement.
// Brute force: O(n^2) time, O(1) space. HashMap: O(n) time, O(n) space.
import java.util.*;

/**
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // array is not sorted

        // i'm going to set p1 at nums[0] until nums[nums.length -2] and p2 from nums[1] and on 
        // looking for that sum 
    
        for(int p1 = 0; p1 < nums.length - 1; p1++){
            for (int p2 = p1 + 1; p2 < nums.length; p2++){
                if (nums[p1] + nums[p2] == target){
                    return new int[]{p1, p2};
                }
            }
        }

        return new int[0];;
    }
} */


// guardo en un mapa clave-valor diciendo: 
// if first elem is not in map, save it [position: element]
// check numsMap elems -> if elem + 

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        int complement = 0;

        for(int i = 0; i < nums.length; i++){
            
            complement = target - nums[i];

            if (numsMap.containsKey(complement)){
                return new int[]{numsMap.get(complement), i};
            }
            numsMap.put(nums[i], i);   
        }

        return new int[0];
    }
}
