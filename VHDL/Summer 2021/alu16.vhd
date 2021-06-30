library ieee;
use ieee.std_logic_1164.all;

library work;
use work.ALU.all;
use work.ControlCircuit.all;
use work.basic_components.all;

-- Declaration of an ALU16 entity.
entity ALU16 is
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
	-- usage of the ALU controller
	Step0: controller port map (operationCode, aInverted, bInverted, operation, c(0));
	-- 16 loops, one for each bit
	G0:
		for i in 0 to 15 generate
		alu: ALU1 port map (a(i), b(i), aInverted, bInverted, c(i), operation, res(i), c(i+1));
	end generate;
	
	-- carryOut is 0 if we ADD/SUB, elsewhere its c(16)
	carryOut <= c(16) when operation = "10" else '0';
	 
	-- overflow check
	checkOverflow1: xor_2 port map (a(15), a(15), isOverflow1);
	checkoverflow2: xor_2 port map (isOverflow1, c(15), isOverflow2);
	checkOverflow3: xor_2 port map (isOverflow2, c(16), isOverflow);

end architecture;
