library ieee;
use ieee.std_logic_1164.all;

package basic_components is 
	-- AND component
	component and_2
		port (in1, in2: in std_logic;
				out1: out std_logic);
	end component;
	
	-- OR component
	component or_2
		port (in1, in2: in std_logic;
				out1: out std_logic);
	end component;
	
	-- XOR component
	component xor_2
		port (in1, in2: in std_logic;
				out1: out std_logic);
	end component;
	
end package basic_components;



-- ορίζουμε τα entity and_2, or_2, xor_2
library ieee;
use ieee.std_logic_1164.all;

entity and_2 is
	port (in1, in2: in std_logic; out1: out std_logic);
end and_2;

architecture and_behavior of and_2 is
begin
	out1 <= in1 and in2;
end and_behavior;

-- code for OR
library ieee;
use ieee.std_logic_1164.all;

entity or_2 is
	port (in1, in2: in std_logic; out1: out std_logic);
end or_2;

architecture or_behavior of or_2 is
begin
	out1 <= in1 or in2;
end or_behavior;

-- code for XOR
library ieee;
use ieee.std_logic_1164.all;

entity xor_2 is
	port (in1, in2: in std_logic; out1: out std_logic);
end xor_2;

architecture xor_behavior of xor_2 is
begin
	out1 <= in1 xor in2;
end xor_behavior;