"""
author: @Dion
content: A Merge Sort algorithm that sorts an array of integers.
Integer Array -> Sorted Integer Array

Operations: <= 6n*log2(n) + 6n          // 6 array accesses at worst case - 2 when copying and 4 when comparing

Space Complexity: O(n)
O(n): O(n log(n))
Θ(n): Θ(n log(n))
Ω(n): Ω(n log(n))

Optimal for: Arrays of size n >=~ 90.
Has a lot of merging overhead, but stable performance in worst case.
"""


def merge_sort(array):
    if len(array) > 1:
        mid = len(array)//2
        left_array = array[:mid]
        right_array = array[mid:]

        merge_sort(left_array)
        merge_sort(right_array)

        i, j, k = 0, 0, 0

        while i < len(left_array) and j < len(right_array):
            if left_array[i] < right_array[j]:
                array[k] = left_array[i]
                i += 1
            else:
                array[k] = right_array[j]
                j += 1
            k += 1

        while i < len(left_array):
            array[k] = left_array[i]
            i += 1
            k += 1
        while j < len(right_array):
            array[k] = right_array[j]
            j += 1
            k += 1
