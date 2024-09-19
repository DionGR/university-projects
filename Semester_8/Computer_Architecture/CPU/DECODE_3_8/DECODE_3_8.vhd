-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity DECODE_3_8 is
port (
		in0 : in std_logic_vector(2 downto 0);
		out0: out std_logic_vector(7 downto 0)
		);
end DECODE_3_8;

architecture logic of DECODE_3_8 is
begin
	with in0 select
	out0 <=  "00000001" when "000",
				"00000010" when "001",
				"00000100" when "010",
				"00001000" when "011",
				"00010000" when "100",
				"00100000" when "101",
				"01000000" when "110",
				"10000000" when "111",
				"00000000" when others;
end logic;