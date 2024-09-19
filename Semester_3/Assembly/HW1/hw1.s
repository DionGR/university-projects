#	Computer Systems Organization
#	Winter Semester 2021-2022
#	1st Assignment
#
# 	Pseudocode by MARIA TOGANTZH (mst@aueb.gr)
#
# 	MIPS Code by DIONYSIOS RIGATOS - P3200262 - p3200262@aueb.gr

    .text
    .globl __start		

# --------------------- Variables ------------------------------------------

#s0: counter
#s1: mask
#s2: result
#s3: power constant
#t0: power variable
#t1: packed value 
#t2: unpacked value

# ------------------- Read and Validate Data ------------------------------

__start:	  		 	               
	li $s0, 4                       # counter = 4 
    li $s3, 16
loop: 		 			            # while counter != 0 do
    beqz $s0, exit_loop             # if counter == 0 goto exit_loop
                                         
    li $v0, 12                      # Read hex character in $v0
    syscall                          

    blt $v0, 48, exit_on_error      # if character is not ('0'..'9') and is not ('A'..'F') then
    blt $v0, 58, isHex              # 			goto exit_on_error
    blt $v0, 65, exit_on_error      # 		else 	
    blt $v0, 71, isHex              # 			goto isHex
    bgt $v0, 70, exit_on_error                              

isHex:					
    sll $t1, $t1, 8     #   shift left $t1
	or $t1, $t1, $v0	# 	pack $v0 to $t1 
	addi $s0, $s0, -1	# 	counter = counter - 1
	
	j loop              # goto loop

# ------------------- Calculate Decimal Number -----------------------------		

exit_loop:					
    li $v0, 4		    # print '\n'
    la $a0, newline	    
    syscall			    

	li $s2, 0		    # result = 0

	li $s0, 4			# counter = 4
						
	li $t0, 1           # power = 1

	li $s1, 255			# $s1 = 255 (mask = 11111111)

loop2:					
	beqz $s0, exit_loop2# while counter != 0 do	
	and $t2, $t1, $s1   # $t2 =  least significant byte from $t1 (unpack)
	srl $t1, $t1, 8     # shift right $t1 
	bgt $t2, 64, caseAF	# if $t2 is letter A..F then 

case09:                 # else 
    addi $t2, $t2, -48  # $t2 = $t2 - 48
    j EndIf

caseAF:
    addi $t2, $t2, -55  # $t2 = $t2 - 55

EndIf:	                     	
	mul	$t2, $t2, $t0	# 	$t2 = $t2 * power
	mul $t0, $t0, $s3   # 	power = power * 16
	addi $s0, $s0, -1	# 	counter = counter - 1
	add $s2, $s2, $t2   # 	result = result + $t2
						
	j loop2				# goto loop2
 

# ------------------- Print Results ------------------------------------		

exit_loop2:			
    move $a0, $s2	# print result
    li $v0, 1		
    syscall         

    j exit			# goto exit

exit_on_error:		
    li $v0, 4		# print '\n'
    la $a0, newline	
    syscall			
 
    li $v0, 4		# print error message
    la $a0, error	
    syscall			
    
    j exit          # goto exit

exit:				
    li $v0, 4		# print '\n'
    la $a0, newline	
    syscall		    
		
    li $v0, 10		# exit program
    syscall			

.data
	error:   .asciiz "Wrong Hex Number ..." # Error Message
    newline: .asciiz "\n"                       # Newline Character
    counter: .word 4
    power:   .word 16