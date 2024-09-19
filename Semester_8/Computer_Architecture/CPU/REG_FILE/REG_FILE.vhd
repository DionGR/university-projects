-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI || P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;

entity REG_FILE is
generic (
			n: integer := 16;
			k: integer := 3;
			regs : integer := 8
		);
		Port (
				clock: in std_logic;
				w1: in std_logic_vector (n-1 downto 0);
				w1ad, r1ad, r2ad: in std_logic_vector (k-1 downto 0);
				r1, r2: out std_logic_vector (n-1 downto 0);
				out0: out std_logic_vector (n * regs - 1 downto 0)
		);
end REG_FILE;

architecture structural of REG_FILE is
	component REG0 is
		port (in0: in std_logic_vector(n-1 downto 0);
				enable, clock: in std_logic;
				out0: out std_logic_vector(n-1 downto 0));
	end component;
	component REG is
		Port (in0: in std_logic_vector(n-1 downto 0);
				enable, clock: in std_logic;
				out0: out std_logic_vector (n-1 downto 0));
	end component;
	component DECODE_3_8 is
		port (in0: in std_logic_vector(k-1 downto 0);
				out0: out std_logic_vector(regs-1 downto 0)
				);
	end component;
	component MUX_8_1 is
		port (in0, in1, in2, in3, in4, in5, in6, in7: in std_logic_vector(n-1 downto 0);
				choice: in std_logic_vector(k-1 downto 0);
				out0: out std_logic_vector(n-1 downto 0)
				);
	end component;
	
	signal enable_signals: std_logic_vector(regs-1 downto 0);
	signal re0, re1, re2, re3, re4, re5, re6, re7: std_logic_vector(n-1 downto 0);

begin 

	G0: DECODE_3_8 port map(w1ad, enable_signals);
	G1: REG0 port map(w1, enable_signals(0), clock, re0);
	G2: REG port map(w1, enable_signals(1), clock, re1);
	G3: REG port map(w1, enable_signals(2), clock, re2);
	G4: REG port map(w1, enable_signals(3), clock, re3);
	G5: REG port map(w1, enable_signals(4), clock, re4);
	G6: REG port map(w1, enable_signals(5), clock, re5);
	G7: REG port map(w1, enable_signals(6), clock, re6);
	G8: REG port map(w1, enable_signals(7), clock, re7);
	G9: MUX_8_1 port map(re0, re1, re2, re3, re4, re5, re6, re7, r1ad, r1);
	G10: MUX_8_1 port map(re0, re1, re2, re3, re4, re5, re6, re7, r2ad, r2);
	
	out0 <= re7 & re6 & re5 & re4 & re3 & re2 & re1 & re0;
	
end structural;