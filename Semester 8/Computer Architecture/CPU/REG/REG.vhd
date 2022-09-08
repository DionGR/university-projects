-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity REG is
generic (
			n: integer := 16
		);
port (
		in0: in std_logic_vector(n-1 downto 0);
		enable, clock: in std_logic;
		out0: out std_logic_vector(n-1 downto 0)
);
END REG;

architecture structural  of REG is
	component D_FF is
		port (in0, clock, enable: in std_logic;
				out0: out std_logic);
	end component;
begin
	G0: D_FF port map(in0(0), clock, enable, out0(0));
	G1: D_FF port map(in0(1), clock, enable, out0(1));
	G2: D_FF port map(in0(2), clock, enable, out0(2));
	G3: D_FF port map(in0(3), clock, enable, out0(3));
	G4: D_FF port map(in0(4), clock, enable, out0(4));
	G5: D_FF port map(in0(5), clock, enable, out0(5));
	G6: D_FF port map(in0(6), clock, enable, out0(6));
	G7: D_FF port map(in0(7), clock, enable, out0(7));
	G8: D_FF port map(in0(8), clock, enable, out0(8));
	G9: D_FF port map(in0(9), clock, enable, out0(9));
	G10: D_FF port map(in0(10), clock, enable, out0(10));
	G11: D_FF port map(in0(11), clock, enable, out0(11));
	G12: D_FF port map(in0(12), clock, enable, out0(12));
	G13: D_FF port map(in0(13), clock, enable, out0(13));
	G14: D_FF port map(in0(14), clock, enable, out0(14));
	G15: D_FF port map(in0(15), clock, enable, out0(15));
end structural;