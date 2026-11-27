# Learning log

Dated notes in my own words. Newest at the bottom.

## 2026-11-25
- Set up two GitHub accounts on one Mac: an SSH host alias per account and a per-folder git identity (includeIf).
- Why it works: SSH picks the key by Host name; git picks the identity by folder.
- Image vs container, in my words: an image is a read-only template: your app, its dependencies and its configuration, built in layers. A container is a running instance of that image, with its own thin writable layer on top. It's like a class and an object: from one image you can run many containers.

## 2026-11-26
- Planned the first Spring Boot project (kept locally for now) to practice REST, validation, persistence and tests step by step.
- Practiced explaining past work out loud, using the STAR structure: situation, task, action, result.
- Reviewed security vocabulary I use at work, to be able to define it precisely:
  - **STRIDE**: a checklist to find threats (Spoofing, Tampering, Repudiation, Information disclosure, Denial of service, Elevation of privilege).
  - **DREAD**: a way to rank the threats found (Damage, Reproducibility, Exploitability, Affected users, Discoverability).
  - **IDOR / BOLA**: the API returns an object just because the caller knows its ID, without checking the caller may see it. Fix: authorize every object access, and don't expose guessable IDs.
  - **HMAC**: a signature made with a shared secret over the request, so the receiver can verify who sent it and that it wasn't modified.
- Idempotency: running an operation twice has the same effect as running it once. It is what makes retries and batch re-runs safe (it's how I made a 2M-user data migration re-runnable).

## 2026-11-27 (live coding)
- **Two Sum (Java).** Brute force with two nested loops is O(n²). With a HashMap (value → index) I check, for each number, whether `target - nums[i]` was already seen: one pass, O(n) time and space. Check the complement first, then store the current number, so an element never pairs with itself. Optimization: one `get` instead of `containsKey` + `get`.
- **Group Anagrams (Java).** Anagrams have the same sorted letters, so the sorted word is the key and the value is the list of words. `Arrays.sort(char[])` sorts in place and returns nothing. Optimization: use the letter counts (26 numbers) as the key instead of sorting: O(n·k) instead of O(n·k log k).
- **Alert Using Same Key-Card 3+ Times (JavaScript).** Group times per person in a Map, convert "HH:MM" to minutes, sort numerically (`(a, b) => a - b`; the default sort compares strings), and compare each time with the one two positions earlier: if the gap is ≤ 60, that person gets an alert. O(n log n).
- Mistakes I made and fixed along the way: loop bounds (`p2 < nums.length`, not `- 1`), resetting `p2` for each `p1`, `===` in JS vs `==` for numbers in Java, Java Map method names (`containsKey`, `put`, `get`), and variables declared twice.
- Pattern across all three: for "have I seen this?" or "what belongs together?", reach for a HashMap, and think about the key.

## Next
- Valid Palindrome (two pointers), then Top K Frequent Elements.
- Push the Spring Boot project and write up what each layer does.
- Docker: write a Dockerfile for it and explain each line.
