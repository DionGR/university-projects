-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity REG0 is
generic (
			n: integer := 16
		);
port (
		in0: in std_logic_vector(n-1 downto 0);
		enable, clock: in std_logic;
		out0: out std_logic_vector(n-1 downto 0)
);
end REG0;

architecture behavior of REG0 is
begin
	out0 <= (others => '0');
end behavior;