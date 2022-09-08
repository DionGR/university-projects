-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity GEQ is
  port (in0: in    std_logic_vector(15 downto 0);
		  out0: out   std_logic_vector(15 downto 0)
		  );
end entity GEQ;

architecture logic of GEQ is

  component NOT_1 is
    port(
      in0: in    std_logic;
      out0: out   std_logic
		  );
  end component NOT_1;

  signal z: std_logic := '0';

begin

  out0(15 downto 1) <= (others => z);

  V0: NOT_1
    port map 
	 (in0  => in0(15),
      out0 => out0(0)
    );

end architecture logic;