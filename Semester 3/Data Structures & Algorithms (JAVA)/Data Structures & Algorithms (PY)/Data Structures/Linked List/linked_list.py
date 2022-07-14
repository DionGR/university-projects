"""
Linked List

author: @Dion
content: A Linked List class with a basic and advanced functions. Can also reverse the list.
basic functions: len, append, remove, get_element

Space Complexity: O(n)
Access: O(n)
Search: O(n)
Insertion: O(1)
Deletion: O(n)
"""


class Node:
    def __init__(self, data=None, next=None):
        self.data = data
        self.next = next


class LinkedList:
    def __init__(self):
        self.head = None
        self.length = 0

    def __len__(self):
        return self.length

    def __str__(self):
        current = self.head
        elements = [current.data]
        while current.next is not None:
            current = current.next
            elements.append(current.data)
        return " -> ".join(elements)

    def append(self, data):
        if self.head is None:   # If there is no data, add as the first element.
            self.head = Node(data, None)
            return
        current = self.head     # Else, iterate through the list and add to the end.
        while current.next is not None:
            current = current.next
        current.next = Node(data,  None)
        self.length += 1

    def append_multiple(self, data_list: list):
        for data in data_list:
            self.append(data)

    def insert(self, index, data):
        if index < 0 or index >= len(self):
            raise Exception("Index out of range.")

        if index == 0:
            self.insert_at_beginning(data)
            return

        count = 0
        current = self.head
        while current is not None:
            if count == index - 1:
                node = Node(data, current.next)
                current.next = node
                self.length += 1
                return
            current = current.next
            count += 1
        

    def insert_at_beginning(self, data):
        node = Node(data, self.head)
        self.head = node
        self.length += 1

    def remove(self, index):
        if index < 0 or index >= len(self):
            raise Exception("Index out of range.")
        self.length -= 1
        if index == 0:
            self.head = self.head.next
            return

        count = 0
        current = self.head
        while current is not None:
            if count == index - 1:
                current.next = current.next.next
                return
            current = current.next
            count += 1

    def get_element(self, index):
        if index >= len(self):
            raise Exception("Index out of range.")

        current_index = 0
        current_Node = self.head
        while True:
            if current_index == index:
                return current_Node.data
            current_Node = current_Node.next
            current_index += 1

    def get_elements(self):
        current = self.head
        elements = [current.data]
        while current.next is not None:
            current = current.next
            elements.append(current.data)
        return elements

    def reverse(self):
        current_node = self.head
        previous_node = None
        while current_node is not None:
            next_node = current_node.next
            current_node.next = previous_node
            previous_node = current_node
            current_node = next_node
        self.head = previous_node
