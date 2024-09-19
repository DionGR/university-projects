# Computer Systems Organization
# Winter Semester 2021-2022
# 2nd Assignment
#
# Pseudocode by MARIA TOGANTZOPOULOS (mst@aueb.gr)


.text

main:
	li $v0, 8 # str = read_string()
	la $a0, str
	li $a1, 128
	syscall

	lb $s1, spac # load space
	la $t1, str 
	lb $t0, ($t1) # capitalize first char of string
	addi $t0, $t0, -32
	sb $t0, ($t1)
	addi $t1, $t1, 1

loop: # load current char
	lb $t0, ($t1)
	beqz $t0, exit # if char is \0 go to exit
	beq $t0, $s1, space # if char is ' ' go to space
	addi $t1, $t1, 1
	j loop

space: # found space, so make capitalize next char
	addi $t1, $t1, 1
	lb $t0, ($t1) # load next char
	addi $t0, $t0, -32 # capitalize next char
	sb $t0, ($t1) # store is his place next char
	addi $t1, $t1, 1 # go to next char
	j loop

exit: # print (str)
	la $a0, str
	li $v0, 4
	syscall

	li $v0,10
	syscall
	# exit

.data
spac: .asciiz " "
str: 	.space 128