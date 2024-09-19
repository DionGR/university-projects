-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;
use ieee.numeric_std.all;

-- FULL ADDER 16
entity FULLADDER_16 is 
	port (in0, in1: in std_logic_vector(15 downto 0);
		  carry_in: in std_logic;
		  sum: out std_logic_vector(15 downto 0);
		  carry_out: out std_logic
         );
end FULLADDER_16;

architecture structural of FULLADDER_16 is

	component FULLADDER_1
	port (in0, in1, carry_in: in std_logic;
		  sum, carry_out: out std_logic
         );
	end component;
	
	signal cout_add: std_logic_vector(15 downto 0);
	begin
		V0: FULLADDER_1 port map (in0(0), in1(0), carry_in, sum(0), cout_add(1));
		V1: FULLADDER_1 port map (in0(1), in1(1), cout_add(1), sum(1), cout_add(2));
		V2: FULLADDER_1 port map (in0(2), in1(2), cout_add(2), sum(2), cout_add(3));
		V3: FULLADDER_1 port map (in0(3), in1(3), cout_add(3), sum(3), cout_add(4));
		V4: FULLADDER_1 port map (in0(4), in1(4), cout_add(4), sum(4), cout_add(5));
		V5: FULLADDER_1 port map (in0(5), in1(5), cout_add(5), sum(5), cout_add(6));
		V6: FULLADDER_1 port map (in0(6), in1(6), cout_add(6), sum(6), cout_add(7));
		V7: FULLADDER_1 port map (in0(7), in1(7), cout_add(7), sum(7), cout_add(8));
		V8: FULLADDER_1 port map (in0(8), in1(8), cout_add(8), sum(8), cout_add(9));
		V9: FULLADDER_1 port map (in0(9), in1(9), cout_add(9), sum(9), cout_add(10));
		V10: FULLADDER_1 port map (in0(10), in1(10), cout_add(10), sum(10), cout_add(11));
		V11: FULLADDER_1 port map (in0(11), in1(11), cout_add(11), sum(11), cout_add(12));
		V12: FULLADDER_1 port map (in0(12), in1(12), cout_add(12), sum(12), cout_add(13));
		V13: FULLADDER_1 port map (in0(13), in1(13), cout_add(13), sum(13), cout_add(14));
		V14: FULLADDER_1 port map (in0(14), in1(14), cout_add(14), sum(14), cout_add(15));
		V15: FULLADDER_1 port map (in0(15), in1(15), cout_add(15), sum(15), carry_out);
		
end structural;