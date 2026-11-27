## 2026-11-25
- Set up two GitHub accounts on one Mac: an SSH host alias per account and a per-folder git identity (includeIf).
- Why it works: SSH picks the key by Host name; git picks the identity by folder.
- Image vs container, in my words:  An image is a read-only template: your app, its dependencies and its configuration, built in layers. A container is a running instance of that image, with its own thin writable layer on top. It's like a class and an object: from one image you can run many containers.

## 2026-11-27 (live coding)
- Two Sum (Java): brute force with two nested loops is O(n²). With a HashMap (value -> index) I check, for each number, whether `target - nums[i]` is already in the map: one pass, O(n) time, O(n) space. Order matters: check the complement first, then store the current number, so an element never pairs with itself.
- Group Anagrams (Java): two words are anagrams if their sorted letters are equal, so the sorted word is the HashMap key and the value is the list of words. `Arrays.sort(char[])` sorts in place and returns nothing. O(n·k log k).
- Alert Using Same Key-Card 3+ Times (JavaScript): group times per person with a Map, convert "hh:mm" to minutes, sort numerically (`(a, b) => a - b`), and compare each time with the one two positions earlier: if the gap is <= 60, that person gets an alert. O(n log n).
- Pattern across all three: when I need "have I seen this before / what belongs together", reach for a HashMap with the right key.
