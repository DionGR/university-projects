		.text
	
	
    li $v0, 8 # str = read_string()
	la $a0, str
	li $a1, 128
	syscall		

    la $t0, str	
    lb $s0, hashtag
    lb $s2, one 
    lb $s3, zero
		            	
				# SEARCH FOR '#'
search:			# ch = 	next char from str
	lb $t1, ($t0) 			# if ch = 0 goto not_ok  (end of string)
	beqz $t1, not_ok			# if ch = '#' goto control -> search for 4 binary digits
	beq $t1, $s0, control 			# 
	addi $t0, $t0, 1
	j search			# goto search
				
control:			# counter = 0 
    li $s1, 0

again:				# if counter == 4 goto ok (4 binary digits)
	slti $t2, $s1, 4			# ch = next char from str
	beqz $t2, ok			# 
	addi $t0, $t0, 1
    lb $t1, ($t0)
    beqz $t1, not_ok			# if ch = 0 	goto not_ok (end of string)
    bgt $t1, $s2, not_ok			# if ch > '1'	goto not_ok (no binary digit)
    blt $t1, $s3, not_ok
				# if ch < '0'	goto not_ok (no binary digit)
	addi $s1, $s1, 1			# counter++
	j again			# goto again

not_ok:				# print (error_message)
	li $v0, 4		
    la $a0, error_mesg	
    syscall	
	
	j exit
		

ok:				# print (no_error_message)
	li $v0, 4		
    la $a0, no_error_mesg	
    syscall	
		
	j exit
	
exit:				# end of program
	li $v0, 10		# exit program
    syscall				

		.data
error_mesg:     	.asciiz "Not OK"
str:				.space 128
no_error_mesg:  	.asciiz "OK"
hashtag:            .asciiz "#"
one:                .asciiz "1"
zero:               .asciiz "0"