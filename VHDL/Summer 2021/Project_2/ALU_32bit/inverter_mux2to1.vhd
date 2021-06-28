library ieee;
use ieee.std_logic_1164.all;

-- ορίζουμε το inverter_mux2to1 package για να χρησιμοποιηθεί και στην ALU
package inverter_mux2to1 is
	component mux2to1
	   -- το in1, in2 είναι οι δύο είσοδοι
		-- η f είναι το αποτέλεσμα
		port (in1, in2, s : in STD_LOGIC;
				f 	: out STD_LOGIC);
	end component;

end package inverter_mux2to1;
	
-- ορίζουμε το mux2to1 entity
library ieee ;
use ieee.std_logic_1164.all ;

entity mux2to1 is
	port (in1, in2, s	: in STD_LOGIC;
			f 	: out STD_LOGIC);
end mux2to1;


architecture inverter_mux2to1_behavior of mux2to1 is
begin
	-- η f γίνεται ίση με την πρώτη είσοδο αν το s είναι 0 αλλίως είναι ίση με την δεύτερη
	WITH s SELECT
		f <= in1 WHEN '0',
			  in2 WHEN OTHERS;
end inverter_mux2to1_behavior;