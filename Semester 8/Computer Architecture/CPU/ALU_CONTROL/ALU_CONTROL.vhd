-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity ALU_CONTROL is
port (  op : in std_logic_vector(3 downto 0);
		func : in std_logic_vector(2 downto 0);
		out0 : out std_logic_vector(3 downto 0)
	);
end ALU_CONTROL;

architecture logic of ALU_CONTROL is
begin
process(op, func) begin
	case op is
		when "0000" =>
		out0(3)<='0';
			out0(2 downto 0)<= func(2 downto 0);
		when others => out0 <= op;
	end case;
end process;
end logic;