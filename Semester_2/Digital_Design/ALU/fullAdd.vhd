LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- Full Adder Component
PACKAGE fullAdd IS
	COMPONENT fullAdd_component
		PORT (Cin, in1, in2 : IN STD_LOGIC;
				 s, Cout 		: OUT STD_LOGIC);
	END COMPONENT;
END PACKAGE fullAdd;

-- Full Adder COMPONENT Initiliazation
LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY fullAdd_component IS
	PORT ( Cin, in1, in2 : IN STD_LOGIC;
			 s, Cout   		: OUT STD_LOGIC );
END fullAdd_component;
-- Behavioral Architecture of a Full Adder as shown in the Laboratories.
ARCHITECTURE fullAdd_behavior OF fullAdd_component IS
BEGIN
	s <= in1 XOR in2 XOR Cin;
	Cout <= (in1 AND in2) OR (Cin AND in1) OR (Cin AND in2);
END fullAdd_behavior;
