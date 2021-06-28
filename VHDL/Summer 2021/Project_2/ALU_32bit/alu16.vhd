library ieee;
use ieee.std_logic_1164.all;

library work;
use work.ALU.all;
use work.ControlCircuit.all;
use work.basic_components.all;

-- ορίζουμε το ALU16 entity
entity ALU16 is
	-- οι a, b είναι οι δύο είσοδοι
	-- το operationCode  είναι το opCode που δίνεται στον circuitController
	-- το res είναι το αποτέλεσμα
	-- το carryOut είναι το τελικό κρατούμενο
	-- το isOverflow ελέγχει αν υπάρχει υπερχείλιση
	port (a, b: in std_logic_vector (15 downto 0);
			operationCode: in std_logic_vector (2 downto 0);
			res: out std_logic_vector (15 downto 0);
			carryOut: out std_logic;
			isOVerflow: out std_logic);
end entity;


architecture structural of ALU16 is	
	signal aInverted, bInverted, isOverflow1, isOverflow2: std_logic;
	signal operation: std_logic_vector(1 downto 0);
	signal c: std_logic_vector(16 downto 0);
begin
	-- χρησιμοποιείται το controller για να επιλεχτεί η πράξη
	Step0: controller port map (operationCode, aInverted, bInverted, operation, c(0));
	-- χρησιμοποιούμε μια λούπα 16 φορές
	G0:
		for i in 0 to 15 generate
		-- χρησιμοποιούμε την ALU ενός bit για να κάνουμε την ίδια πράξη για κάθε από τα 16 bit
		alu: ALU1 port map (a(i), b(i), aInverted, bInverted, c(i), operation, res(i), c(i+1));
	end generate;
	
	-- το τελικό κρατούμενο είναι 0 όταν δεν έχουμε πρόσθεση ή αφαίρεση αλλιώς είναι ίση με το c(16)
	carryOut <= c(16) when operation = "10" else '0';
	
	-- ελέγχουμε αν υπάρχει υπερχείλιση
	checkOverflow1: xor_2 port map (a(15), a(15), isOverflow1);
	checkoverflow2: xor_2 port map (isOverflow1, c(15), isOverflow2);
	checkOverflow3: xor_2 port map (isOverflow2, c(16), isOverflow);

end architecture;