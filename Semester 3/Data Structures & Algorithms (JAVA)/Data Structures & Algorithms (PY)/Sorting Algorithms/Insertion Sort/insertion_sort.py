"""
author: @Dion
content: An insertion sort algorithm that sorts an array of integers.
Integer Array -> Sorted Integer Array

Space Complexity: O(1)
O(n): O(n^2)
Θ(n): Θ(n^2)
Ω(n): Ω(n)

Optimal for: Arrays of size n <= ~ 90 or semi-sorted arrays.
Has very low overhead and is stable.
"""


def insertion_sort(array):
    for i in range(1, len(array)):
        j = i   # I - 1 is the number of already sorted elements.
        while array[j - 1] > array[j] and j > 0:
            array[j - 1], array[j] = array[j], array[j - 1]
            j -= 1
    return array
