-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

entity MUX_2_1 is
    port(add_c, sub_c: in std_logic;
         s:     in std_logic_vector(3 downto 0);
         out0: out std_logic);
end MUX_2_1;

architecture logic of MUX_2_1 is
begin
	  with s select
			out0 <=   add_c when "0000", -- Only used for carry_out in the ALU, 
						 sub_c when "0001", -- Pick carry out depending on signal input
						 '0' when others;
end logic;