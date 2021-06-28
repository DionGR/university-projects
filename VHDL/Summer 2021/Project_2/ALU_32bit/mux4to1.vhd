library ieee;
use ieee.std_logic_1164.all;


-- ορίζουμε το mux4to1 package για να χρησιμοποιηθεί στην ALU
package mux4to1 is
	component mux4to1Component
		-- τα w0, w1, w2, w3 είναι οι είσοδοι του πολυπλέκτη
		-- το s χρησιμοποιήται για να επιλέξουμε μια από τις εισόδους
		-- το res είναι το αποτέλεσμα, δηλαδή η είσοδος που έχει επιλεγεί
		port (w0, w1, w2, w3: in STD_LOGIC ;
				s: in STD_LOGIC_VECTOR(1 downto 0);
				res: out STD_LOGIC);
	end component;
end package mux4to1;


-- ορίζουμε το mux4to1Component entity
library ieee;
use ieee.std_logic_1164.all;

entity mux4to1Component is
	port (w0, w1, w2, w3 : in STD_LOGIC ;
			s: in STD_LOGIC_VECTOR(1 downto 0);
			res: out STD_LOGIC);
end mux4to1Component ;

architecture mux4to1_behavior of mux4to1Component is
begin
	-- το αποτέλεσμα είναι ίσο με την πρώτη είσοδο αν το s είναι 00, ίσο με τη δεύτερη αν είναι 01
	-- ίσο με την τρίτη αν είναι ίσο με 10, ίσο με την τέταρτη αν είναι ίσο με 11
	with s select
	res <= w0 when "00",
	w1 when "01",
	w2 when "10",
	w3 when others;
end mux4to1_behavior;
