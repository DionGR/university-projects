-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

entity MUX_8_1_ALU is
	port (add_op, sub_op, and_op, or_op, geq_op, not_op: in std_logic_vector(15 downto 0);
			s: 	in std_logic_vector(3 downto 0);
			out0: out std_logic_vector(15 downto 0));
	end MUX_8_1_ALU;

architecture logic of MUX_8_1_ALU is
begin
	with s select -- Pick an operation for the ALU depending on the signal input
		out0 <=   add_op when "0000",
					 sub_op when "0001",
					 and_op  when "0010",
					 or_op when "0011",
					 geq_op when "0100",
					 not_op when "0101",
					 "0000000000000000" when others;
end logic;