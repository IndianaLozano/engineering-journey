# Live coding

Interview-style problems, solved first by me, then commented and optimized. Each file has the approach and complexity in its header and a small test harness at the bottom.

| # | Problem | Language | Pattern | Time / Space | File |
|---|---------|----------|---------|--------------|------|
| 1 | [Two Sum](https://leetcode.com/problems/two-sum/) | Java | HashMap: value → index, look up the complement | O(n) / O(n) | [`java/TwoSum.java`](java/TwoSum.java) |
| 49 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Java | HashMap keyed by sorted letters, then by letter counts | O(n·k) / O(n·k) | [`java/GroupAnagrams.java`](java/GroupAnagrams.java) |
| 1604 | [Alert Using Same Key-Card 3+ Times](https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/) | JavaScript | Group by key, sort, compare `t[i]` with `t[i-2]` | O(n log n) / O(n) | [`javascript/alertNames.js`](javascript/alertNames.js) |

## Run
```
java java/TwoSum.java
java java/GroupAnagrams.java
node javascript/alertNames.js
```
Needs JDK 11+ and Node 16+.

## The pattern behind these
When the question is "have I seen this before?" or "what belongs together?", use a HashMap and spend the thinking on the key.
