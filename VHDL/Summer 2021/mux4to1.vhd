LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- 4to1 Multiplexer Component
PACKAGE mux4to1 IS
	COMPONENT mux4to1_component
		PORT (in0, in1, in2, in3 : IN STD_LOGIC ;
				s 						 : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
				res 					 : OUT STD_LOGIC);
	END COMPONENT;
END PACKAGE mux4to1;

-- 4to1 Multiplexer Component Initialization
LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY mux4to1_component IS
	PORT (in0, in1, in2, in3 : IN STD_LOGIC ;
			s 						 : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
			res 					 : OUT STD_LOGIC);
END mux4to1_component;

ARCHITECTURE mux4to1_behavior OF mux4to1_component IS
BEGIN
	WITH s SELECT
	res <= in0 WHEN "00",
	in1 WHEN "01",
	in2 WHEN "10",
	in3 WHEN OTHERS;
END mux4to1_behavior;