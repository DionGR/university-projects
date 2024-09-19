-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity REGISTER_IF_ID is
	generic (
		n : integer := 16
	);
	port (
  		inPC, inInstruction: in std_logic_vector(n-1 downto 0);
  		clock, IF_Flush, IF_ID_ENABLE: in std_logic;
  		outPC, outInstruction: out std_logic_vector(n-1 downto 0)
	 );
end REGISTER_IF_ID;


architecture logic of REGISTER_IF_ID is
begin

pc: process(clock, IF_Flush, IF_ID_ENABLE)
begin
	if clock='1' and IF_ID_ENABLE='1' then
		outPC <= std_logic_vector(unsigned(inPC) + 2);
		outInstruction <= inInstruction;
	elsif clock='1' and IF_Flush='1' then
		outPC <= (others => '0');
		outInstruction <= (others => '0');
	end if;
end process pc;

end logic;