// LeetCode 49. Group Anagrams: https://leetcode.com/problems/group-anagrams/
// Pattern: HashMap with a canonical key (the word's letters sorted).
// Time O(n * k log k), space O(n * k), n words of length up to k.
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>(); 

        for (String word : strs) {
        
            char[] chars = word.toCharArray();
            // 1. sort chars
            Arrays.sort(chars);
            // 2. build the key: new String(chars)
            String key = new String(chars);

            // 3. if the key isn't in the map yet, put a new ArrayList
            if (!groups.containsKey(key)){
                groups.put(key, new ArrayList<>());
            }

            // 4. add the word to that key's list
            groups.get(key).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
