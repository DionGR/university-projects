-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS
library ieee;
use ieee.std_logic_1164.all;


entity FULLSUBTRACTOR_16 is
	port (  in0,in1: in std_logic_vector (15 downto 0);
			  carry_in: in std_logic;
			  sub: out std_logic_vector (15 downto 0);
			  carry_out: out std_logic);
end FULLSUBTRACTOR_16;

architecture structural of FULLSUBTRACTOR_16 is
	 
    component FULLADDER_16 
		 port (		  
		 carry_in: in std_logic;
		 in0, in1: in std_logic_vector(15 downto 0);
		  sum: out std_logic_vector(15 downto 0);
		  carry_out: out std_logic
        );
    end component;
	 
	 signal complement, complement_1, minus_in1: std_logic_vector(15 downto 0);
    signal cout_sub: std_logic;
	 
begin
    generate_label:
    for i in 0 to 15 generate
			  complement(i) <= NOT in1(i);
    end generate;
	 
    complement_1 <= "0000000000000001";
    CMPL: FULLADDER_16  port map(carry_in, complement, complement_1, minus_in1, cout_sub);
    RES: FULLADDER_16  port map(carry_in, in0, minus_in1, sub, carry_out);
	 
end structural;