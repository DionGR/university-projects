-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- XOR_2 Gate
entity XOR_2 is
port(   in0, in1: in std_logic;
        out0: out std_logic
    );
end XOR_2;

architecture logic of XOR_2 is
begin
    out0 <= in0 XOR in1;
end logic;