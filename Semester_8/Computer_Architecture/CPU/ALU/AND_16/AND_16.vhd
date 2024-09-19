-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- AND Gate
entity AND_16 is
	port (in0, in1: in std_logic_vector(15 downto 0);
			out0: out std_logic_vector(15 downto 0));
end AND_16;

architecture logic of AND_16 is
begin
	out0 <= in0 and in1;
end logic;