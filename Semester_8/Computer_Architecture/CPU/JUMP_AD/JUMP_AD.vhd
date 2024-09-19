-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity JUMP_AD is
	generic (
				n: integer := 16;
				k: integer := 12
			);
	port (
			jumpADR : in std_logic_vector(k-1 downto 0);
			instrP2AD : in std_logic_vector(n-1 downto 0);
			EjumpAD :out std_logic_vector(n-1 downto 0)
	);
end JUMP_AD;

architecture logic of JUMP_AD is
	signal extended, multed : std_logic_vector(n-1 downto 0);
begin
	extended<= (n-1 downto k => jumpADR(k-1)) & (jumpADR);
		
	process(instrP2AD)
	begin
		multed<= extended(n-2 downto 0) & '0';
		EjumpAD<= std_logic_vector( unsigned(multed)+ unsigned(instrP2AD));
	end process;
end logic;