-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity CONTROL is
	port (opCode: in std_logic_vector(3 downto 0);
			func: in std_logic_vector(2 downto 0);
			flush: in std_logic;
		   isMPFC, isJumpD, isReadDigit, isPrintDigit, isR, isLW, isSW, isBranch, isJR: out std_logic);
end CONTROL;

architecture logic of CONTROL is

begin
	get_result: process (flush, func, opCode) begin 
		if flush = '1' then
			isR <='0'; 
			isMPFC <='0';
			isLW <='0';
			isSW <='0';
			isBranch <='0';
			isReadDigit <='0';
			isPrintDigit <='0';
			isJumpD <='0';
			isJR <='0';
		end if;
		
		if flush = '0' then
			case opCode is 
				when "0000" =>
					isR <= '1';
					if func = "111" then
						isMPFC <= '1';
					end if;
				when "0001" =>
					isLW <= '1';
				when "0010" =>
					isSW <= '1';
				when "0100" =>
					isBranch <= '1';
				when "0110" =>
					isReadDigit <= '1';
				when "0111" =>
					isPrintDigit <= '1';
				when "1111" =>
					isJumpD <= '1';
				when "1101" =>
					isJR <= '1';
				when others =>
					isR <= '0';
			end case;
		end if;
	end process;

end logic;