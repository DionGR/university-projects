-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity TRAP_UNIT is
port (
	opcode: in std_logic_vector(3 downto 0);
    EOR : out std_logic
);
end TRAP_UNIT;

architecture logic of TRAP_UNIT is

begin
 process (opcode)
 begin
     if opcode = "1110" then
       EOR <= '1';
     else
	    EOR <= '0';
     end if;
 end process;

end logic;
