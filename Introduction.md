# Introduction to DP
>Those who cannot remember the past are condemned to repeat it.

Dynamic Programming can be described as storing answers to various sub-problems to be used later whenever required to solve the main problem.

According to Freecodecamp:
>Dynamic programming amounts to breaking down an optimization problem into simpler sub-problems, and storing the solution to each sub-problem so that each sub-problem is only solved once

Unlike standard algorithms like Merge Sort or Djikstra's Shortest Path, dynamic programming is not a specific kind of algorithm that provides the blueprint to solve a problem rather it is a blueprint for the strategy one should adopt to help solve a problem either partially or fully. The main idea is to look for repetitive work or overlapping subproblems or substructure pattern(usually observed more in recursive algorithms) and avoid them by caching the result for a given input. 

The two common dynamic programming approaches are:

1. Memoization: Known as the “top-down” dynamic programming, usually the problem is solved in the direction of the main problem to the base cases.
2. Tabulation: Known as the “bottom-up ” dynamic programming, usually the problem is solved in the direction of solving the base cases to the main problem


To demonstrate the usage/implementation of DP, we will use the example
> Finding the N<sup>th</sup> Fibonacci Number

Fibonacci Series is defined as every i-th number of the series is equal to the sum of (i-1)th and (i-2)th number where the first and second number is given as 0 and 1 respectively.

Example : 0,1,1,2,3,5,8,13,21,…

More formally, Fib(n) = Fib(n-1) + Fib(n-2) ; given that Fib(0)=0 and Fib(1)=1

The elegant solution to this is:
```java
public long Fib(int n){
    if(n==0 || n==1) return n;
    return Fib(n-1)+Fib(n-2);
}
```

The recursion tree for the above block of code is

```
                            fib(5)   
                        /                \
                   fib(4)                fib(3)   
                /        \              /       \ 
            fib(3)      fib(2)         fib(2)   fib(1)
            /    \       /    \        /      \
        fib(2)   fib(1)  fib(1) fib(0) fib(1) fib(0)
        /     \
        fib(1) fib(0)
```

Look closely and you will see that some of the nodes already computed on the left subtree is again being called for on the right subtree. When size of the input grows, the number of recomputations increase as well. Standing here what can we do to avoid these time consuming repetitions?

If somehow, we could store the values returned by nodes on the left subtree in a Data Structure, we can easily reuse them later on the right subtree. A HashMap can pop up in our minds at this stage but lets think about it for a while. We see that for every node an unique value exists and therefore an array would serve the purpose.

Let us create an array - `long[] dp = new long[n+1]`

Creating an array of size `n+1` as arrays follow an zero based indexing and we like to return `dp[n]` as the answer to keep our code aesthetic and easy to read.

Now, simply before we start calculating the N-th Fibonacci Number we will check if we have calculated it previously. Also, before we return our answer we will store the answer in the array for future reference.

```java
public static long[] dp = new long[n+1]; //N can be obtained from Constraints
public long Fib(int n){
    if(n==0||n==1) return n;
    if(dp[n]!=-1) return dp[n];
    dp[n] = Fib(n-1) + Fib(n-2);
    return dp[n];
}
```

The above process is called Memoization. This is a top-down approach as we progress towards the base case.

The next DP technique that we will explore for the same problem is called Tabulation.

Tabulation is a ‘bottom-up’ approach where we start from the base case and reach the final answer that we want.

Steps to convert Recursive Solution to Tabulation:

1. Declare a ```dp[]``` array of size ```n+1```.
2. First initialize the base condition values, i.e i=0 and i=1 of the dp array as 0 and 1 respectively.
3. Set an iterative loop which traverses the array( from index 2 to n) and for every index set its value as ```dp[i-1] + dp[i-2]```. 

```java
public long Fib(int n){
    long[] dp = new long[n+1];
    Arrays.fill(dp,-1);
    dp[0]= 0;
    dp[1]= 1;
    for(int i=2; i<=n; i++){
        dp[i] = dp[i-1]+ dp[i-2];
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
public long Fib(int n){
    if(n==0||n==1) return n;
    int minus2 = 0;
    int minus1 = 1;
    for(int i=2; i<=n; i++){
        int curr = minus2 + minus;
        minus2 = minus;
        minus = curr;
    }
    return minus;
}
```

Time Complexity: O(N) 
Space Complexity: O(1)

