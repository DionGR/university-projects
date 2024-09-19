-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity PC_REGISTER is
  port (in0: in std_logic_vector(15 downto 0); 
		  Enable, Clock: in std_logic; 
		  out0: out std_logic_vector(15 downto 0)); 
end PC_REGISTER; 

architecture logic of PC_REGISTER is
begin
  process (Clock)
  begin
    if Clock'event and Clock = '0' then
      if Enable = '1' then
        out0 <= in0;
      end if;
    end if;
  end process;

end logic;