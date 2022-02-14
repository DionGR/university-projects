-- AND Gate
library IEEE;
use ieee.std_logic_1164.all;

entity AND_2 is
	port (in1, in2: in std_logic; out1: out std_logic);
end AND_2;

architecture and_struct of AND_2 is
begin
	out1 <= in1 and in2;
end and_struct;

-- OR gate

library IEEE;
use ieee.std_logic_1164.all;

entity OR_2 is
	port (in1, in2: in std_logic; out1: out std_logic);
end OR_2;

architecture or_struct of OR_2 is
begin
	out1 <= in1 or in2;
end or_struct;
	
-- Main program
library IEEE;
use ieee.std_logic_1164.all;

entity askhsh1 is
	port (x1, x2, x3, x4, x5: in std_logic;
			f: out std_logic);
end askhsh1;


architecture structural of askhsh1 is
	component AND_2
		port(in1, in2: in std_logic;
				out1: out std_logic);
	end component;
	component OR_2
		port (in1, in2: in std_logic;
				out1: out std_logic);
	end component;
	signal A1,A2,A3,A3_1,A4,A4_1,A5,A5_1,O1,O2,O3:std_logic;
	
begin
	U1:AND_2 port map (not x3, not x5, A1);
	U2:AND_2 port map (not x4, not x5, A2);
	U3:AND_2 port map (not x1, x2, A3_1);
	U4:AND_2 port map (A3_1, not x5, A3);
	U5:AND_2 port map (not x1, not x2, A4_1);
	U6:AND_2 port map (A4_1, not x4, A4);
	U7:AND_2 port map (x2, x4, A5_1);
	U8:AND_2 port map (A5_1, x5, A5);
	U9:OR_2 port map (A1, A2, o1);
	U10:OR_2 port map (A3, A4, o2);
	U11:OR_2 port map (o1, o2, o3);
	U12:OR_2 port map (o3, A5, f);
	--f <= (NOT x3 AND NOT x5) OR (NOT x4 AND NOT x5) OR (NOT x1 AND x2 AND NOT x5) OR (NOT x1 AND NOT x2 AND NOT x4) OR (x2 AND x4 AND x5)
end structural;