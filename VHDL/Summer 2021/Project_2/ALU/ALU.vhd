LIBRARY ieee ;
USE ieee.std_logic_1164.all ;

-- Importing the WORK library and all the packages/components we have created in other files.
LIBRARY work;
USE work.basic_components.all;
USE work.fullAdd.all;
USE work.mux2to1.all;
USE work.mux4to1.all;
	
-- Declaration of our ALU entity according to the diagram provided.
ENTITY ALU IS
	PORT (a, b, CarryIn		: IN 	STD_LOGIC;
			AInvert, BInvert	: IN 	STD_LOGIC;		
			Operation			: IN 	STD_LOGIC_VECTOR(1 DOWNTO 0);
			CarryOut, Result	: OUT STD_LOGIC);
END ALU;

-- Our Structural code that connects all our previously created components, creating our ALU.
ARCHITECTURE Structural OF ALU IS	
	SIGNAL aInverted, bInverted, andRes, orRes, addRes, xorRes : STD_LOGIC;
BEGIN
	AInverter: inverter_mux2to1 PORT MAP (a, not a, AInvert, aInverted);
	BInverter: inverter_mux2to1 PORT MAP (b, not(b), BInvert, bInverted);
	AND_2		: AND_2_GATE PORT MAP (aInverted, bInverted, andRes);
	OR_2		: OR_2_GATE PORT MAP (aInverted, bInverted, orRes);
	fullAdd	: fullAdd_component PORT MAP (CarryIn, aInverted, bInverted, addRes, CarryOut);
	XOR_2		: XOR_2_GATE PORT MAP (aInverted, bInverted, xorRes);
	Final		: mux4to1_component PORT MAP (andRes, orRes, addRes, xorRes, Operation(1 DOWNTO 0), Result);
END Structural;