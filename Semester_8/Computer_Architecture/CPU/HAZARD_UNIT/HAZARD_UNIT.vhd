-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity HAZARD_UNIT is
 port (
		   isJR, isJump, wasJump, mustBranch : in std_logic;
		   flush, wasJumpOut : out std_logic;
		   JRopcode: out std_logic_vector(1 downto 0)
 );
end HAZARD_UNIT;

architecture logic of HAZARD_UNIT is
begin
 --OPCODE:
 -- 00: +2
 -- 01: JumpADd
 -- 10: BranchAd
 process (isJR, isJump, wasJump,mustBranch)
 begin
	if isJR = '1' or isJump = '1' or wasJump = '1' or mustBranch = '1' then
		flush <= '1';
	else
		flush <= '0';
	end if;
	if isJump = '1' then
		JRopcode <= "01";
	elsif mustBranch = '1' then
		JRopcode <= "10";
	else
		JRopcode <= "00";
	end if;	
 end process;
 
	wasJumpOut <= isJump;
 
end logic;