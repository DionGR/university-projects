-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity JR_SELECTOR is
generic (
		n : integer := 16
	);
port (
   jumpAD, branchAd, PCP2AD: in std_logic_vector(n-1 downto 0);
   JRopcode: in std_logic_vector(1 downto 0);
   result: out std_logic_vector(n-1 downto 0)
);
end JR_SELECTOR;

architecture logic of JR_SELECTOR is
begin
 --OPCODE:
 -- 00: +2
 -- 01: JumpADd
 -- 10: BranchAd
 process (JRopcode)
 begin
	case JRopcode is
		when "00"  => 
			result <= PCP2AD;
		when "01"  => 
			result <= jumpAD;
		when "10"  => 
			result <= branchAd;
		when others => result <= PCP2AD;
	end case;		
 end process;
 
end logic;