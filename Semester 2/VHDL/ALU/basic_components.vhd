LIBRARY ieee;
USE ieee.std_logic_1164.all;

PACKAGE basic_components IS
	-- AND Component
	COMPONENT AND_2_GATE
		PORT (in1, in2 : IN STD_LOGIC;
				andRes 	: out STD_LOGIC);
	END COMPONENT;
	-- OR Component
	COMPONENT OR_2_GATE
		PORT (in1, in2 : IN STD_LOGIC;
				orRes 	: out STD_LOGIC);
	END COMPONENT;
	-- XOR Component
	COMPONENT XOR_2_GATE
		PORT (in1, in2 : IN STD_LOGIC;
				xorRes 	: out STD_LOGIC);
	END COMPONENT;		
END PACKAGE basic_components;
		
-- AND Gate COMPONENT Initialization 
LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY AND_2_GATE IS
	PORT (in1, in2 : IN STD_LOGIC; 
			andRes	: out STD_LOGIC);
END AND_2_GATE;

ARCHITECTURE and_behavior OF AND_2_GATE IS
BEGIN
	andRes<= in1 and in2;
END and_behavior;

-- OR Gate COMPONENT Initialization
LIBRARY IEEE;
USE ieee.std_logic_1164.all;

ENTITY OR_2_GATE IS
	PORT (in1, in2 : IN STD_LOGIC; 
			orRes		: out STD_LOGIC);
END OR_2_GATE;

ARCHITECTURE or_behavior OF OR_2_GATE IS
BEGIN
	orRes <= in1 or in2;
END or_behavior;

-- XOR Gate COMPONENT Initialization
LIBRARY IEEE;
USE ieee.std_logic_1164.all;

ENTITY XOR_2_GATE IS
	PORT (in1, in2 : IN STD_LOGIC; 
			xorRes	: out STD_LOGIC);
END XOR_2_GATE;

ARCHITECTURE xor_behavior OF XOR_2_GATE IS
BEGIN
	xorRes <= in1 xor in2;
END xor_behavior;