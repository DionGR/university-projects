library ieee ;
use ieee.std_logic_1164.all ;

-- ορίζουμε το package fullAdd γιατί χρησιμοποιείται στην ALU
package fullAdd is
	component fullAdder
		-- το cin είναι το κρατούμενο από πρίν
		-- τα in1, in2 είναι οι είσοδοι
		-- το s είναι το αποτέλεσμα της πράξης
		-- το cout είναι το καινούργιο κρατούμενο 
		port (Cin, in1, in2 : in STD_LOGIC;
				 s, Cout: out STD_LOGIC);
	end component;
end package fullAdd;
	
	
-- ορίζουμε το fullAdder entity
library ieee ;
use ieee.std_logic_1164.all;
library work;
use work.basic_components.all;

entity fullAdder is
	port ( Cin, in1, in2 : in STD_LOGIC;
			 s, Cout   		: out STD_LOGIC );
end fullAdder;

architecture fullAdd_behavior of fullAdder is
begin
	-- το s είναι 1 αν υπάρχει περιττός αριθμός από 1
	s <= in1 XOR in2 XOR Cin;
	-- το cout είναι 1 αν και οι δύο είσοδοι είναι 1 ή μια είσοδος είναι 1 και το κρατούμενο είναι 1
	Cout <= (in1 AND in2) OR (Cin AND in1) OR (Cin AND in2);
end fullAdd_behavior;
