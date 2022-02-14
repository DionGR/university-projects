#	Computer Systems Organization
#	Winter Semester 2021-2022
#	3rd Assignment
#
# 	MIPS Code by:
#   Athanasios Trifonis p3200298@aueb.gr, P3200298,
#   Dionysios Rigatos p3200262@aueb.gr, P3200262.

        .text
        .globl main
main:
        li $s1, 0               # List size = 0

# This is the main menu. Here we print a prompt to
# the user, with the available choices next to a 
# number from 1 to 3 for each choice. After that,
# the user inputs an integer to the console.
menu:
        la $a0, menuMsg         # Menu options.
        li $v0, 4
        syscall

        li $v0, 5               # User option selection.
        syscall

        beq $v0, 1, insertion
        beq $v0, 2, deletion
        beq $v0, 3, printList
        j exit                  # Go to exit if option != 1, 2, 3.

# Insert(x) : The user types a value (x) and a new node is created
# with the specified value, inside the sorted linked list. If the
# list is empty the node is inserted as first and last node by
# getting its address saved in register $t0 and by setting 0 as link. 
insertion:
        la $a0, newNodeMsg      # Node value prompt.
        li $v0, 4               
        syscall
        li $v0, 5               # $v0 = input.
        syscall
        move $s0, $v0           # $s0 = $v0.
        beq $s1, 0, insertFirst

# Get the first node address and value, then compare it with the input
# value. If the input value is bigger goto function next which traverses the
# list searching for the correct place make the insertion.
# The insertion is made when the input value is greater or equal to the
# current node value.
createNode:
        la $s7, ($t0)           # First node address. 
        lw $s6, ($s7)           # First node value.
        slt $t2, $s0, $s6       # Is the input value lower than the first?
        beqz $t2, next          # If false goto next.

# The address of the created node is saved as a pointer to the first node.
#       call malloc;    
#       $t0 = allocated address $v0;
#       current.value = $s0;            ( $s0 has the input value. It is saved in the allocated address $t0 first's position)  
#       current.next = 0;               ( The link is 0 and is saved in the second position)
#       size++;                         ( $s1: size)
insertFirst:        
        jal malloc              # Allocate new memory.
        move $t0, $v0           # $t0 = allocated address. 
        sw $s0, ($t0)           # Node value (from input).
        sw $s7, 4($t0)          # Next node.
        add $s1, $s1, 1         # Increment size.
        bne $s1, 1, menu

# Sets 0 as node's link.
firstAndLast:
        sw $zero, 4($t0)        # Set 0 as link.  cur.next = 0
        j menu

# Traverse the linked list from the first node, until you find
# a current node with a smaller value. Then insert the new node as
# current.next. If the last node of the list is reached goto insertLast.
next:
        la $s4, ($s7)           
        lw $s5, 4($s7)          # $s5 = Next address.
        beqz $s5, insertLast    # Check if address 0 is reached, if true goto insertLast.
        lw $s7, 4($s7)          # Next node address.
        lw $s6, ($s7)           # Next node value.
        slt $t2, $s0, $s6       # $t2 = inputVal < current.val ? 1 : 0 
        beqz $t2, next          # if $t2 = 0 goto next.
        jal malloc              # The new node is smaller than the current, so it 
        move $t3, $v0
        sw $s0, ($t3)           # current node value assignment.
        sw $s7, 4($t3)          # cur.next = next
        sw $t3, 4($s4)          # prev.next = cur
        add $s1, $s1, 1
        j menu

# Insert node as last node. Allocate memory for the node, store
# the input value to the first 4 bytes and 0 to the other 0 bytes, as a link.
# Give the allocated address of the new node as a link to the old last node.
#       create new node (node)
#       node.value = inputVal
#       node.next = 0
#       oldLast.next = node 
insertLast:
        jal malloc
        move $t3, $v0
        sw $s0, ($t3)           # New node
        sw $zero, 4($t3)        # last --> 0
        sw $t3,  4($s7)         # Oldlast --> last
        add $s1, $s1, 1
        j menu

# delete(x) :  The user types a value of the node that he wants to
# be deleted. If the list is empty there is a message that informs
# the user about it. If there are nodes in the list search the list
# for the user specified node value.   
deletion:
        beqz $s1, emptyList     # If the list is empty.

        la $a0, delPrompt
        li $v0, 4
        syscall

        li $v0, 5               # Node for deletion.
        syscall

# Get the first node and if it is not the node for deletion goto delRep.
# If the first node is the node to delete check if its the only node of
# the list, if it is go to delOnlyNode, else goto delFirst.
#       if (first.val != inputVal) { goto delRep }
#       else {
#           if (size = 1) { goto delOnlyNode }     
#           else { goto delFirst }
#       }
search:
        la $s7, ($t0)           # $s7 = First node address.
        lw $s6, ($s7)           # $s6 = First node value.
        bne $s6, $v0, delRep
        beq $s1, 1, delOnlyNode       

# Gets the second node address and then setting that address to the register $t0 which points
# to the first node of the link.
#       first = first.next
#       size--
delFirst:
        lw $s5, 4($t0)          # Next node.
        la $t0, ($s5)           # Old second is now first.
        addi $s1, $s1, -1       # Reduce size by 1.

        la $a0, deleted
        li $v0, 4
        syscall

        j menu

# Change the size of the list to 0. Then the next insertion
# will have to create a new first and last node from which the
# list will be starting.
delOnlyNode:
        move $s1, $zero         # Set list size to 0.

        la $a0, deleted
        li $v0, 4
        syscall

        j menu

# Search inside the list's nodes until you find the node to delete
# or until the list's end.
#       while ( current.val != inputVal && current.next != 0 )
#           prev = prev.next
#           current = current.next    
#     if (current.next == 0) { goto delLast } 
#     if (current.val == inputVal) { goto del }
delRep:
        la $s4, ($s7)           # prev address
        lw $s5, 4($s7)          # prev.next , cur
        lw $s6, ($s5)           # cur.value
        lw $s3, 4($s5)          # cur.next
        beqz $s3, delLast       # If last node.
        beq $s6, $v0, del       # Found node.
        lw $s7, 4($s7)          # prev = prev.next
        lw $s5, 4($s5)          # current = current.next
        lw $s3, 4($s3)          # $s3 = current.next (To check if the last node is reached).
        j delRep

# If the input value is different than the last node in the list
# goto delFail (the node for deletion is not in the list).
# Else set 0 as link the the previous node from the deleted last
# node.
#       if (node.value != inputVal) { goto delFail }
#           else {
#               prev.next = 0 
#               size--
#           }  
delLast:
        bne $s6, $v0, delFail   # Check if the inputVal is in the last node. If not goto delFail.
        sw $zero, 4($s7)        # prev.next = 0
        addi $s1, $s1, -1       # size--
        
        la $a0, deleted         # Print succesful deletion message.
        li $v0, 4
        syscall
        
        j menu

# Get the next address from the node you will delete, then
# set the previous node pointing the next.
#       prev.next = current.next
#       size-- 
del:
        la $s5, 4($s5)          # Next address.
        lw $s4, ($s5)

        sw $s4, 4($s7)          # prev.next = cur.next

        la $a0, deleted         # Print succesful deletion message.
        li $v0, 4
        syscall

        addi $s1, $s1, -1
        j menu

# Informs the user that the selected node is not in the list.
delFail:
        la $a0, delFailMsg      # Print a failed deletion message.
        li $v0, 4
        syscall
        j menu

# Prints every node inside the sorted list. The list is always sorted in
# ascended order.  
# If the list is empty it branches to the empty list message function.
# ($s1: size, $t0 is used as a pointer to the first node.)
printList:
        beqz $s1, emptyList
        la $s7, ($t0)           # First node address.
        lw $s6, ($s7)           # Node value.

# Traverse the list and print each node value until you
# find the node that has 0 as a link (last node).
#       while (cur.next != 0):
#               print(cur.value)
#               cur = cur.next
#       goto menu
repeatPrint:
        move $a0, $s6           # Print node value.
        li $v0, 1
        syscall

        la $a0, points          # Node1 -> Node2 etc.
        li $v0, 4
        syscall

        lw $s5, 4($s7)          # Checks if next is 0. $s5 = cur.next
        beqz $s5, menu
        la $s7, ($s5)           # Next node address.
        lw $s6, ($s7)           # Next node value.
        j repeatPrint

# Informs the user that the list is empty. It is getting called
# when the user tries to print an empty list or delete from an empty
# list.
emptyList:
        la $a0, emptyListMsg    # Prints that you have an empty list.
        li $v0, 4
        syscall
        j menu

# Allocates 8 bytes of memory
# and returns the allocated address $v0 in the program. 
malloc:
        li $a0, 8               # Memory allocation.
        li $v0, 9
        syscall
        jr $ra

        
# Exiting program with a message.
exit:   
        la $a0, extMsg          # Exiting msg.
        li $v0, 4
        syscall

        li $v0, 10
        syscall

        .data
menuMsg:        .asciiz "\nChoose the corresponding number for the action that you want:\n1) Node insertion\n2) Node deletion\n3) Print list\n\n"
extMsg:         .asciiz "Program exiting...\n"
emptyListMsg:   .asciiz "The list is empty!\n"
newNodeMsg:     .asciiz "Give a value for the new node:\n"
delPrompt:      .asciiz "Select node for deletion:\n"
deleted:        .asciiz "Deleted node!\n"
delFailMsg:     .asciiz "The selected node does not exist!\n"
points:         .asciiz " -> "