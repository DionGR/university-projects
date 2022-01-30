LIBRARY ieee;
USE ieee.std_logic_1164.all;

-- 2to1 Multiplexer Component for inverting a/b
PACKAGE mux2to1 IS
	COMPONENT inverter_mux2to1
		PORT (in1, in2 	: IN STD_LOGIC;
				s				: IN STD_LOGIC;
				invertRes 	: out STD_LOGIC);
	END COMPONENT;
END PACKAGE mux2to1;

-- 2to1 Multiplexer COMPONENT Initialization 
LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY inverter_mux2to1 IS
	PORT (in1, in2 	: IN STD_LOGIC;
			s				: IN STD_LOGIC;
			invertRes 	: out STD_LOGIC);
END inverter_mux2to1;

ARCHITECTURE inverter_mux2to1_behavior OF inverter_mux2to1 IS
BEGIN
	WITH s SELECT
		invertRes <= in1 WHEN '0',
						 in2 WHEN OTHERS;
END inverter_mux2to1_behavior;