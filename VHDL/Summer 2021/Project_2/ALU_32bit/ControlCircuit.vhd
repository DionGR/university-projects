library ieee;
use ieee.std_logic_1164.all;

-- ορίζουμε το ControlCircuit package που χρησιμοποιείται για να δείξουμε ποια πράξη διαλέξαμε
package ControlCircuit is
	component controller
		-- opcode είναι ο κωδικός της πράξης
		-- Ainvert, Binvert είναι οι δύο είσοδοι
		-- operation είναι ποιά πράξη έχει επιλεχτεί
		-- carryIn είναι το κρατούμενο
		port (opcode: in std_logic_vector(2 downto 0);
				Ainvert, Binvert: out std_logic;
				operation: out std_logic_vector(1 downto 0);
				carryIn: out std_logic);
	end component;
end package ControlCircuit;

-- ορίζουμε το controller entity
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
-- ελέγχουμε τις περιπτώσεις με βάση τον δωσμένο πίνακα
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