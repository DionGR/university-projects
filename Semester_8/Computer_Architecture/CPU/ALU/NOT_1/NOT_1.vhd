-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- NOT GATE
entity NOT_1 is
port( in0: in std_logic;
		out0: out std_logic
	 );
end NOT_1;

architecture logic of NOT_1 is
begin
	out0 <= not in0;
end logic;