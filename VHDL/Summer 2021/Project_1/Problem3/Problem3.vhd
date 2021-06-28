library IEEE;
use ieee.std_logic_1164.all;

ENTITY Problem3 IS
PORT (x1, x2, x3, x4 : IN STD_LOGIC;
		f					: OUT STD_LOGIC);
END problem3;

ARCHITECTURE Behavior OF problem3 IS
BEGIN
		f <= (not x1 and x2) or (x3 and x4);
END Behavior;
