// LeetCode 1604. Alert Using Same Key-Card Three or More Times in a One Hour Period
// https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
// Pattern: group by key (Map), sort, then compare each time with the one two positions earlier.
// Time O(n log n), space O(n).

/**
 * @param {string[]} keyName
 * @param {string[]} keyTime
 * @return {string[]}
 */

 /**
<= 60 mins
keyTime NOT chronologically

group per person 

convert hh:mm to integer minute count so comparisons are simple 


  */
var alertNames = function(keyName, keyTime) {

    const toMinutes = (hhmm) => {
        const [h, m] = hhmm.split(":").map(Number);
        return h * 60 + m;
    }

    // 1. group timestamps in minutes per person
    const timesByPerson = new Map();
    for (let i = 0; i < keyName.length; i++){
        const name = keyName[i];
        const mins = toMinutes(keyTime[i]);
        if (!timesByPerson.has(name)){
            timesByPerson.set(name, []);
        }
        timesByPerson.get(name).push(mins);
    }

    // 2. for each person, sort times and check if any 3 consecutives swipes are <= 60 mins apart
    const alerts = [];
    for (const [name, times] of timesByPerson.entries()){
        if (times.length < 3) continue;

        times.sort((a, b) => a - b)

        for (let i = 2; i < times.length; i++){
            if (times[i] - times[i - 2] <= 60){
                alerts.push(name);
                break;
            }
        }
    }

    return alerts.sort();
};
