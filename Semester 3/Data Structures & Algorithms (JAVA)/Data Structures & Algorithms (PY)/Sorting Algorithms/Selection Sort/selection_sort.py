"""
author: @Dion
content: A Selection Sort algorithm that sorts an array of integers.
Integer Array -> Sorted Integer Array

Space Complexity: O(1)
O(n): O(n^2)
Θ(n): Θ(n^2)
Ω(n): Ω(n^2)

Optimal for: Checking if something is sorted, low memory usage.
"""


def selection_sort(array):
    for i in range(0, len(array) - 1):
        current_min_i = i 
        for j in range(i + 1, len(array)):
            if array[j] < array[current_min_i]:
                current_min_i = j
        
        array[i], array[current_min_i] = array[current_min_i], array[i]