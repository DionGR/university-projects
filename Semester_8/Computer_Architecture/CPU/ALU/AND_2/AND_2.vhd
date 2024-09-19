-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- 2BIT IN >> 1BIT OUT AND_2 Gate --
entity AND_2 is 
port ( in0: in std_logic;
		 in1: in std_logic;
		 out0: out std_logic
	  );
end AND_2;

architecture logic of AND_2 is
begin
	out0 <= in0 and in1;
end logic;