-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity REGISTER_MEM_WB is
	generic (
		n : integer := 16;
		addressSize : integer := 3
	);
	port(	Result: in std_logic_vector(n-1 downto 0);
			RegAD: in std_logic_vector(addressSize-1 downto 0);
			clock: in std_logic;
			writeData: out std_logic_vector(n-1 downto 0);
			writeAD: out std_logic_vector(addressSize-1 downto 0)
	 );
end REGISTER_MEM_WB;

architecture logic of REGISTER_MEM_WB is
begin

pc: process(clock)
begin
	if clock='1' then -- rising edge
		writeData <= Result;
		writeAD <= RegAD;
	end if;
end process pc;

end logic;