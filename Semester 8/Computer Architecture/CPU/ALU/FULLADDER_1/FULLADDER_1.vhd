-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- FULLADDER 
entity FULLADDER_1 is 
	port (in0, in1, carry_in: in std_logic;
			sum, carry_out: out std_logic);
end FULLADDER_1;

architecture structural of FULLADDER_1 is

	component AND_2
	port (in0, in1: in std_logic;
			out0: out std_logic
			);
	end component;

	component OR_3
	port (in0, in1, in2: in std_logic;
			out0: out std_logic
			);	
	end component;
	
	component XOR_3
	port (in0, in1, in2: in std_logic;
			out0: out std_logic
			);
	end component;
	
	signal s1, s2, s3: std_logic;
	begin
		V0: XOR_3 port map (in0, in1, carry_in, sum);
		V1: AND_2 port map (in0, in1, s1);
		V2: AND_2 port map (in0, carry_in, s2);
		V3: AND_2 port map (carry_in, in1, s3);
		V4: OR_3  port map (s1, s2, s3, carry_out);
		
end structural;