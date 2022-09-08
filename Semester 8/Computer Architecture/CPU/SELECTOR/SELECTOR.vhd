-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity SELECTOR is
	generic (n: integer := 16);
	port (Reg, Memory, WriteBack: in std_logic_vector(n-1 downto 0);
			operation: in std_logic_vector(1 downto 0);
		   out0: out std_logic_vector(n-1 downto 0));
end SELECTOR;

architecture logic of SELECTOR is

begin
	with operation select
		out0 <= Reg when "00",
				  WriteBack when "01",
				  Memory when "10",
				  "0000000000000000" when "11";

end logic;

-- process(operation, Memory, WriteBack) begin
	-- case operation is
		-- when "00" => out0 <= Reg;
		-- when "01" => out0 <= WriteBack;
		-- when "10" => out0 <= Memory;
		-- when "11" => out0 <= "0000000000000000";
	-- end case;
-- end process;