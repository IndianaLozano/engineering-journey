/**
 * LeetCode 1604. Alert Using Same Key-Card Three or More Times in a One Hour Period
 * https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 *
 * keyName[i] used their key-card at keyTime[i] ("HH:MM", 24h, same day, NOT in chronological order).
 * Return, sorted alphabetically, the names of people who used it 3+ times within 60 minutes.
 *
 * Run:  node alertNames.js
 */

/**
 * @param {string[]} keyName
 * @param {string[]} keyTime
 * @return {string[]}
 *
 * Idea:
 *  1. Group the times per person, as minutes since midnight (integers are easy to compare).
 *  2. Sort each person's times. In a sorted list, three swipes fit in 60 minutes
 *     exactly when times[i] - times[i - 2] <= 60, so only that pair needs checking.
 *  3. Sort the names that triggered an alert.
 *
 * Time O(n log n) (sorting), space O(n).
 */
var alertNames = function (keyName, keyTime) {
    // "HH:MM" -> minutes. Slicing avoids creating an array like split(":").map(Number) does.
    const toMinutes = (hhmm) => Number(hhmm.slice(0, 2)) * 60 + Number(hhmm.slice(3));

    // 1. group the times per person
    const timesByPerson = new Map();
    for (let i = 0; i < keyName.length; i++) {
        const name = keyName[i];
        if (!timesByPerson.has(name)) {
            timesByPerson.set(name, []);
        }
        timesByPerson.get(name).push(toMinutes(keyTime[i]));
    }

    // 2. sort each person's times and look for 3 swipes within an hour
    const alerts = [];
    for (const [name, times] of timesByPerson) {
        if (times.length < 3) continue; // can't reach 3 swipes: skip the sort

        times.sort((a, b) => a - b); // numeric sort; the default sort compares as strings

        for (let i = 2; i < times.length; i++) {
            if (times[i] - times[i - 2] <= 60) {
                alerts.push(name);
                break; // one alert per person is enough
            }
        }
    }

    // 3. names alphabetically
    return alerts.sort();
};

// ---- tiny test harness ----

function check(name, actual, expected) {
    const ok = JSON.stringify(actual) === JSON.stringify(expected);
    console.log(`${ok ? "PASS" : "FAIL"} ${name} -> ${JSON.stringify(actual)}`);
    if (!ok) process.exitCode = 1;
}

if (require.main === module) {
    check("example 1",
        alertNames(["daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"],
                   ["10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"]),
        ["daniel"]);
    check("example 2",
        alertNames(["alice", "alice", "alice", "bob", "bob", "bob", "bob"],
                   ["12:01", "12:00", "18:00", "21:00", "21:20", "21:30", "23:00"]),
        ["bob"]);
    check("exactly 60 minutes counts",
        alertNames(["a", "a", "a"], ["10:00", "10:30", "11:00"]), ["a"]);
    check("61 minutes does not",
        alertNames(["a", "a", "a"], ["10:00", "10:30", "11:01"]), []);
    check("names are sorted",
        alertNames(["z", "z", "z", "b", "b", "b"], ["01:00", "01:10", "01:20", "02:00", "02:10", "02:20"]),
        ["b", "z"]);
}

module.exports = alertNames;
