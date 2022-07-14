"""
Stack (Linked List implementation)

author: @Dion
content: A stack implemented with Linked Lists
basic functions: peek, push, pop, empty

Space Complexity: O(n)
Access: O(n)
Search: O(n)
Insertion: O(1)
Deletion: O(1)
"""


class Node:
    def __init__(self, data=None, next=None):
        self.data = data
        self.next = next


class stack:

    def __init__(self):
        self.head = Node()
        self.pointer = 0

    def __len__(self):
        return self.pointer 
    
    def __str__(self):
        current = self.head.next 
        content = ""
        while current is not None:
            content += str(current.data) + " <- "
            current = current.next 
        return content[:-4]
    
    def empty(self):
        return self.pointer == 0

    def peek(self):
        if self.empty():
            raise Exception("The stack is empty.")
        return self.head.next.data 
    
    def push(self, data):
        self.head.next = Node(data, self.head.next)
        self.pointer += 1
    
    def pop(self):
        if self.empty():
            raise Exception("The stack is empty.")
        popped = self.head.next.data
        self.head.next = self.head.next.next 
        self.pointer -= 1
        return popped


