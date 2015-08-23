# SubstringDiff
Substring diff problem from Hackerrank.

Substring Diff

Given two strings of length n, P = p1p2...pn and Q = q1q2...qn, we define M(i,j,k) as the number of mismatches between p(i),p(i+1),...p(i+k−1) and q(j),q(j+1)...,q(j+k−1). That is, in set notation, M(i,j,k) refers to the size of the set

{0 <= x < k, p[i+x]| ≠ q[j+x]}
Given an integer S, your task is to find the maximum length L, such that there exists a pair of indices (i,j) for which we have M(i,j,L)≤S. Of course, we should also have i+L−1≤n and j+L−1≤n.

Source: https://www.hackerrank.com/challenges/substring-diff
