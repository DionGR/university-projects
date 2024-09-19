-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;


entity D_FF is
port (in0, clock, enable: in std_logic ;
	   out0: out std_logic ); 
end D_FF;

architecture logic of D_FF is 
 
   signal P1, P2, P3, P4, five, six, afterClock : std_logic;
	
begin
	P3 <= P1 NAND p4;
	P1 <= afterClock NAND P3;
	P2 <= NOT(afterClock AND P1 AND P4);
	P4 <= in0 NAND P2;
	five <= six NAND P1;
	six <= P2 NAND five;
	afterClock <= clock AND enable;
	
	out0 <= five;
	
end logic;