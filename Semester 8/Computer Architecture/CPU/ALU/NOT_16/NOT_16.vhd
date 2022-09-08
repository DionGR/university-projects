-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- NOT_!6 Gate
entity NOT_16 is
	port (in0: in std_logic_vector(15 downto 0);
			out0: out std_logic_vector(15 downto 0)
			);
end NOT_16;

architecture structural of NOT_16 is
begin
    generate_label:
    for i in 1 to 15 generate
        out0(i)<='0';
    end generate;
    out0(0) <= NOT ( in0(15) OR in0(14) OR in0(13) OR in0(12) OR in0(11) OR in0(10) OR in0(9) OR in0(8) OR in0(7) OR in0(6) OR in0(5) OR in0(4) OR in0(3) OR in0(2) OR in0(1) OR in0(0));
end structural;