-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;

-- XOR_3 Gate 
entity XOR_3 is
port(   in0: in std_logic;
        in1: in std_logic;
        in2: in std_logic;
        out0: out std_logic
    );
end XOR_3;

architecture logic of XOR_3 is
begin
    out0 <= in0 xor in1 xor in2;
end logic;