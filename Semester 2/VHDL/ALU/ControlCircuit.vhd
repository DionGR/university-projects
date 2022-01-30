library ieee;
use ieee.std_logic_1164.all;

-- The ControlCircuit package controls which operation we'll do
package ControlCircuit is
	component controller
		-- opcode = operation code (i.e. ADD/SUB/XOR)
		port (opcode: in std_logic_vector(2 downto 0);
				Ainvert, Binvert: out std_logic;
				operation: out std_logic_vector(1 downto 0);
				carryIn: out std_logic);
	end component;
end package ControlCircuit;

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity controller is
	port (
		opcode: in std_logic_vector(2 downto 0);
		Ainvert, Binvert: out std_logic;
		operation: out std_logic_vector(1 downto 0);
		carryIn: out std_logic);
		
end controller;

architecture behavioral of controller is
begin
	process (opcode)
	begin
		case opcode is
			
			when "000" => 
				operation <= "00";
				Ainvert <= '0';
				Binvert <= '0';
				carryIn <= '0';
				
			when "001" =>
				operation <= "01";
				Ainvert <= '0';
				Binvert <= '0';
				carryIn <= '0';
				
			when "010" => 
				operation <= "10";
				Ainvert <= '0';
				Binvert <= '0';
				carryIn <= '0';
				
			when "011" =>
				operation <= "10";
				Ainvert <= '0';
				Binvert <= '1';
				carryIn <= '1';
				
			when "100" =>
				operation <= "00";
				Ainvert <= '1';
				Binvert <= '1';
				carryIn <= '0';
				
			when "101" =>
				operation <= "01";
				Ainvert <= '1';
				Binvert <= '1';
				carryIn <= '0';
				
			when "110" =>
				operation <= "11";
				Ainvert <= '0';
				Binvert <= '0';
				carryIn <= '0';
				
			when others =>
				operation <= "00";
				Ainvert <= '0';
				Binvert <= '0';
				carryIn <= '0';
				
		end case;
	end process;
end behavioral;
