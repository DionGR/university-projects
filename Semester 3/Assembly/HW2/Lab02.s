#    Computer Systems Organization
#    Winter Semester 2021-2022
#    2nd Assignment
#
#     Pseudocode by MARIA TOGANTZH (mst@aueb.gr)
#
#     MIPS Code by 
#     (Please note your name, e-mail and student id number)

        .text
        li $t0,0         # i = 0
        li $v0,5
        lw $t9,N
        syscall # x = readInt()

while1:    beq $v0,$zero,exit1         # if (x == 0) goto exit1

        sw $v0,pin($t0)        # pin[i] = x
        addi $t0,$t0,4        # next i
        beq $t0,$t9,exit1        # if (pinakas gematos) goto exit1 
        li $v0,5         # x = readInt()
        syscall         #
        j while1         # goto while1

exit1:


        li $v0,5         # t1 = readInt()
        syscall
        add $t1,$v0,$zero        #
        mul $t1,$t1,4        #
        li $v0,5        # t2 = readInt()
        syscall        #
        add $t2,$v0,$zero        #
        mul $t2,$t2,4
                        # t1 = apostasi se bytes toy t1 stoixeioy tou pin apo tin arxi toy pin 
                        # t2 = apostasi se bytes toy t2 stoixeioy tou pin apo tin arxi toy pin 

while2:
        bgt $t1,$t2,exit2        # if (t1 > t2) goto exit2)
                # 
        lw $a0,pin($t1)        # print (pin[t1])
        li $v0,1    #
        syscall
        la $a0,keno# print (" ")
        li $v0,4
        syscall        #
        addi $t1,$t1,4        # next t1
                # 
        j while2        # goto while2

exit2:    li $v0,10        # exit
        syscall        #

    .data
N:        .word 40
pin:     .space 40        # 10 akeraioi
keno:    .asciiz ' '