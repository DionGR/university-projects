.text
.globl main
main:
    li $v0,8
    la $a0,str
    li $a1,128
    syscall

    la $t0,str
    la $t2,str1
    la $t3,str2
next:
    lb $t1,($t0)
    beqz $t1,print_results
    bgt $t1,122,storeInStr2
    blt $t1,97,storeInStr2
storeInStr1:
    sb $t1,($t2)
    addi $t0,$t0,1
    addi $t2,$t2,1
    j next
storeInStr2:
    sb $t1,($t3)
    addi $t0,$t0,1
    addi $t3,$t3,1
    j next
print_results:
    la $a0,str1
    li $v0,4
    syscall
    la $a0,enter
    li $v0,4
    syscall
    la $a0,str2
    li $v0,4
    syscall
exit:
    li $v0,10
    syscall



.data
str: .space 128
str1:    .space 128
str2:    .space 128
enter:    .asciiz "\n"