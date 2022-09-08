-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- AND_3 Gate 
entity AND_3 is
port( in0, in1, in2: in std_logic;
		out0: out std_logic
	  );
end AND_3;
		
architecture logic of AND_3 is
begin 
	out0 <= in0 and in1 and in2;
end logic;
		
		
		