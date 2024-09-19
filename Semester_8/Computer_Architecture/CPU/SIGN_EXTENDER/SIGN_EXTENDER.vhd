-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity SIGN_EXTENDER is
	generic (
				n: integer := 16;
				k: integer := 6
			);
	port (
			immediate: in std_logic_vector(k-1 downto 0);
			extended: out std_logic_vector(n-1 downto 0)
	);
end SIGN_EXTENDER;

architecture logic of SIGN_EXTENDER is
begin
	extended <= (n-1 downto k => immediate(k-1)) & (immediate);
END logic;