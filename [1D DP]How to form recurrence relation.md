# How to come up with the recurrence relation

## Characteristics of a DP Problem:
- Overlapping Subproblems - When the solutions to the same subproblems are needed repetitively for solving the actual problem. The problem is said to have overlapping subproblems property.
- Optimal Substructure - If the optimal solution of the given problem can be obtained by using optimal solutions of its subproblems then the problem is said to have Optimal Substructure Property.

## Identifying a DP Problem :
It can be a DP problem if it asks for the following:
- Count total number of unique ways to reach destination or final state. 
- Given multiple ways to reach a final destination / state, find which way will be the maximum or minimum, or best or worst. It is about maximising or minimising a certain quantity
- All dynamic programming problems satisfy the overlapping subproblems property and most of the classic Dynamic  programming problems also satisfy the optimal substructure property.

Let us look into the problem, [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

The elegant recursive solution would be
```java
public int climbStairs(int n){
    if(n==0) return 1;
    if(n<0) return 0;
    return climbStairs(n-1)+climbStairs(n-2);
}
```
## Adapting recursive solution to DP solution:
- Represent problem in terms of index
- According to problem statement, perform every possible action we can perform from the given state.
- If question asks, <br>1. Count all ways -> return sum of all choices; <br> 2. Maximize / Minimize any given quantity -> return choice with max / min output;


We can easily identify that it can optimised with dynamic programming considering the first point to identify DP problems. We will assume **n** stairs as indexes from **0 to n**. At a single time, **we have 2 choices**: Jump **one** step or jump **two** steps. We will try both of these options at every index. As the problem statement asks to **count the total number of distinct ways**, we will return the **sum of all the choices in our recursive function**. The base case will be when we want to go to the 0th stair, then we have only one option. If we are in 0th stair, it means we have reached our destination.

We can express a state in terms of n. Apply memoization so that ```dp[n]``` gives the answer for ```climbStairs(int n)```.

Memoization :
```java
public static int[] dp;
static{
    dp = new int[46];
    Arrays.fill(dp,-1);
}
public int climbStairs(int n) {
    if(n==0) return 1;
    if(n<0) return 0;
    if(dp[n]==-1) dp[n] = climbStairs(n-1)+climbStairs(n-2);
    return dp[n];
}
```

Tabulation:
```java
public int climbStairs(int n){
    int[] dp = new int[n+1];
    dp[0] = 1;
    dp[1] = 1;
    for(int i=2;i<=n;i++){
        dp[i] = dp[i-1]+dp[i-2];
    }
    return dp[n];
}
```

We have the best time complexity possible for the given problem. But we can still slice off some space and it is possible to solve the problem without using an array for the Tabulation Method. If we closely look the relation,

`dp[i] =  dp[i-1] + dp[i-2]`

we see that for any i, we do need only the last two values in the array. So is there a need to maintain a whole array for it? 

The answer is ‘No’. Let us call `dp[i-1]` as `minus1` and `dp[i-2]` as `minus2`. Now understand the following illustration.
![Illustration showing usage of previous two pointers](https://lh6.googleusercontent.com/CkdUwBPuh9mtKv-uVAkg298tPX_KiZ3wWjip8tlCGyREKYxzKdF9iNm6Ml43HlpCYsH8C4twCVkSfvh873eLFigcuDUBsqD6VyNtKuyKTqm7zVMcF45pzNioh38lfmKKr1BDnoF4)

Space Optimized Tabulation Version:
```java
public int climbStairs(int n){
    int minus2 = 1;
    int minus1 = 1;
    for(int i=2;i<=n;i++){
        int curr = minus2 + minus1;
        minus2 = minus1;
        minus1 = curr;
    }
    return minus1;
}
```

### Original Solution:

Time Complexity: O(2<sup>N</sup>)

Space Complexity: O(N)



### DP Optimized:
Time Complexity: O(N)

 Space Complexity: O(1)