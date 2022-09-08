-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity OR_2 is
port( in0, in1: in std_logic;
      out0: out std_logic);
end OR_2;

architecture logic of OR_2 is
begin
    out0 <= in0 or in1;
end logic;