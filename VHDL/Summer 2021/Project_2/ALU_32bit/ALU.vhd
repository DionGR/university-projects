library ieee;
use ieee.std_logic_1164.all;

-- Ορίζουμε το ALU package για να μπορούμε να την χρησιμοποιήσουμε στην ALU 16
package ALU is
	component ALU1
		-- το a είναι η πρώτη είσοδος και το b η δεύτερη
		-- το aInvert χρησιμοποιείται για να αντιστρέψουμε τα bit του a
		-- το bInvert χρησιμοποιείται για να αντιστρέψουμε τα bit του b
		-- το cIn είναι το κρατούμενο που δεχόμαστε από πριν
		-- το s έχει 2 bit και δείχνει την πράξη
		-- το f είναι το αποτέλεσμα της πράξης και το cout το κρατούμενο
		port (a, b, aInvert, bInvert, cIn: in std_logic;
				s: in std_logic_vector(1 downto 0);
				f, cout: out std_logic);
	end component;
end package ALU;

library ieee;
use ieee.std_logic_1164.all;
library work;
-- κάνουμε import τα components xor, and, or
use work.basic_components.all;
-- κάνουμε import τον πολυπλέκτη 2 -> 1
use work.inverter_mux2to1.all;
-- κάνουμε import τον full adder
use work.fullAdd.all;
-- κάνουμε import τον πολυπλέκτη 4 -> 1
use work.mux4to1.all;

-- ορίζουμε το entity ALU
entity ALU1 is
	port (a, b, aInvert, bInvert, cIn: in std_logic;
			s: in std_logic_vector(1 downto 0);
			f, cOut: out std_logic);
end ALU1;

architecture structural of ALU1 is
	signal aInverted, bInverted, andRes, orRes, addRes, xorRes: std_logic;
begin
	-- ελέγχεται αν αντιστραφούν τα bit του a
	Step0: mux2to1 port map (a, not a, aInvert, aInverted);
	-- ελέγχεται αν αντιστραφούν τα bit του b
	Step1: mux2to1 port map (b, not b, bInvert, bInverted);
	-- υλοποιείται η and gate (ή nor gate)
	Step2: and_2 port map (aInverted, bInverted, andRes);
	-- υλοποιείται η or gate (ή nand gate)
	Step3: or_2 port map (aInverted, bInverted, orRes);
	-- υλοποιείται η πράξη της πρόσθεσης (ή αφαίρεσης)
	Step4: fullAdder port map (cIn, aInverted, bInverted, addRes, cOut);
	-- υλοποιείται η xor gate
	Step5: xor_2 port map (aInverted, bInverted, xorRes);
	-- διαλέγεται η πράξη και εκτελείται
	Step6: mux4to1Component port map (andRes, orRes, addRes, xorRes, s(1 downto 0), f);
end structural;