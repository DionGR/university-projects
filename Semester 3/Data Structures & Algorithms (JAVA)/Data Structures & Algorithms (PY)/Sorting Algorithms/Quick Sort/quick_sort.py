"""
author: @Dion
content: A Quick Sort algorithm that sorts an array of integers.
Integer Array -> Sorted Integer Array

Space Complexity: O(log(n))
O(n): O(n^2)
Θ(n): Θ(n log(n))
Ω(n): Ω(n log(n))

Optimal for: Smaller data structures
Good cache locality, in-place with low overhead and quick but unstable.
Faster on average, 2-3 times than merge sort or heap sort.
"""


def quick_sort(array, left, right):
    if left < right:
        partition_position = partition(array, left, right)
        quick_sort(array, left, partition_position - 1)
        quick_sort(array, partition_position + 1, right)


def partition(array, left, right):
    i = left
    j = right - 1
    pivot = array[right]

    while i < j:
        while i < right and array[i] < pivot:
            i += 1
        while j > left and array[j] >= pivot:
            j -= 1

        if i < j:
            array[i], array[j] = array[j], array[i]

    if array[i] > pivot:
        array[i], array[right] = array[right], array[i]

    return i
