-- P3180026 DESPOINA GEORGIADI || P3180103 OLGA MARGELI | P3200262 DIONYSIOS RIGATOS

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;


entity PROCESSOR is
port(
	keyData, fromData, instr: in std_logic_vector(15 downto 0);
	clock, clock2: in std_logic;
	printEnable, keyEnable, DataWriteFlag: out std_logic;
	dataAD, toData, printCode, printData, instructionAD: out std_logic_vector(15 downto 0);
	regOUT: out std_logic_vector(143 downto 0)
);
end PROCESSOR;

architecture structural of PROCESSOR is

-- COMPONENTS

component ALU_16 is
		port(	in0, in1: in std_logic_vector(15 downto 0);
				carry_in: in std_logic;
	   			op: in std_logic_vector(3 downto 0);
	  			 output: out std_logic_vector(15 downto 0);
				carry_out: out std_logic);
END component ALU_16;

component ALU_CONTROL is
	port (  op : in std_logic_vector(3 downto 0);
			func : in std_logic_vector(2 downto 0);
			out0 : out std_logic_vector(3 downto 0)
	);
END component ALU_CONTROL;

component REG_FILE is
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
END component REG_FILE;

component SIGN_EXTENDER is
	generic (
		n : integer := 16;
		k : integer := 6
	);
	port (	
		immediate : in std_logic_vector (k-1 downto 0);
		extended : out std_logic_vector (n-1 downto 0)
	);
END component SIGN_EXTENDER;

component JR_SELECTOR is
	generic (
			n : integer := 16
		);
	port (	jumpAD, branchAd, PCP2AD: in std_logic_vector(n-1 downto 0);
			JRopcode: in std_logic_vector(1 downto 0);
			result: out std_logic_vector(n-1 downto 0)
	);
END component JR_SELECTOR;

component FORWARDER is
	generic (addr_size: integer := 3);
	port (  R1AD, R2AD, RegAD_EXMEM, RegAD_MEMWB: in std_logic_vector(addr_size-1 downto 0);
			S1, S2: out std_logic_vector(1 downto 0)
	);
END component FORWARDER;

component CONTROL is
	port (	opCode: in std_logic_vector(3 downto 0);
			func: in std_logic_vector(2 downto 0);
			flush: in std_logic;
		   	isMPFC, isJumpD, isReadDigit, isPrintDigit, isR, isLW, isSW, isBranch, isJR: out std_logic
	);
END component CONTROL;

component JUMP_AD is
	generic (	n: integer := 16;
				k: integer := 12
			);
	port (	jumpADR : in std_logic_vector(k-1 downto 0);
			instrP2AD : in std_logic_vector(n-1 downto 0);
			EjumpAD :out std_logic_vector(n-1 downto 0)
	);
END component JUMP_AD;

component HAZARD_UNIT is
	port (	isJR, isJump, wasJump, mustBranch : in std_logic;
			flush, wasJumpOut : out std_logic;
			JRopcode: out std_logic_vector(1 downto 0)
	);
END component HAZARD_UNIT;

component TRAP_UNIT is
	port (	opcode: in std_logic_vector(3 downto 0);
			EOR : out std_logic
	);
END component TRAP_UNIT; 

component REGISTER_EX_MEM is
	generic (	n : integer := 16;
			addressSize : integer := 3
	);
	port (	clock, isLW, WriteEnable, ReadDigit, PrintDigit: in std_logic;
			R2Reg, Result: in std_logic_vector(n-1 downto 0);
			RegAD: in std_logic_vector(addressSize-1 downto 0);
			isLW_EXMEM, WriteEnable_EXMEM, ReadDigit_EXMEM, PrintDigit_EXMEM: out std_logic;
			R2Reg_EXMEM, Result_EXMEM: out std_logic_vector(n-1 downto 0);
			RegAD_EXMEM: out std_logic_vector(addressSize-1 downto 0)
	);
end component REGISTER_EX_MEM;

component REGISTER_ID_EX is
	generic (	n : integer := 16;
		addressSize : integer := 3
	);
	port ( 	clock, isEOR, wasJumpOut, isJump, isJR, isBranch, isR, isMFPC, isLW, isSW, isReadDigit, isPrintDigit: in std_logic;
			ALUFunc: in std_logic_vector(3 downto 0);
			R1Reg, R2Reg, immediate16: in std_logic_vector(n-1 downto 0);
			R2AD, R1AD: in std_logic_vector(addressSize-1 downto 0);
			jumpShortAddr: in std_logic_vector(11 downto 0);
			isEOR_IDEX, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX, isBranch_IDEX, isR_IDEX, isMFPC_IDEX, isLW_IDEX, isSW_IDEX, isReadDigit_IDEX, isPrintDigit_IDEX: out std_logic;
			ALUFunc_IDEX: out std_logic_vector(3 downto 0);
			R1Reg_IDEX, R2Reg_IDEX, immediate16_IDEX: out std_logic_vector(n-1 downto 0);
			R2AD_IDEX, R1AD_IDEX: out std_logic_vector(addressSize-1 downto 0);
			jumpShortAddr_IDEX: out std_logic_vector(11 downto 0)
	);
end component REGISTER_ID_EX;

component REGISTER_IF_ID is
	generic ( n : integer := 16
	);
	port (	inPC, inInstruction: in std_logic_vector(n-1 downto 0);
			clock, IF_Flush, IF_ID_ENABLE: in std_logic;
			outPC, outInstruction: out std_logic_vector(n-1 downto 0)
	);
end component REGISTER_IF_ID;

component REGISTER_MEM_WB is
	generic ( n : integer := 16;
			  addressSize : integer := 3
	);
	port(	Result: in std_logic_vector(n-1 downto 0);
			RegAD: in std_logic_vector(addressSize-1 downto 0);
			clock: in std_logic;
			writeData: out std_logic_vector(n-1 downto 0);
			writeAD: out std_logic_vector(addressSize-1 downto 0)
	);
end component REGISTER_MEM_WB;

component PC_REGISTER IS
	port (	in0: in std_logic_vector(15 downto 0); 
			Enable, Clock: in std_logic; 
			out0: out std_logic_vector(15 downto 0)
	); 
END component PC_REGISTER; 

component SELECTOR IS
	generic (  n: integer := 16);
	port (	Reg, Memory, WriteBack: in std_logic_vector(n-1 downto 0);
			operation: in std_logic_vector(1 downto 0);
			out0: out std_logic_vector(n-1 downto 0)
	);
end component SELECTOR;

-- SIGNALS

signal PCresult: STD_LOGIC_VECTOR(15 DOWNTO 0):= x"0000";
signal outIFID_PC, outIFID_Instr, JROutput, immediate16, immediate16_IDEX,  
		  JumpAD, writeData, Result_MEMWB, R1Reg, R2Reg, R1Reg_IDEX, R2Reg_IDEX, 
		  R2Reg_EXMEM, S1Output, S2Output : STD_LOGIC_VECTOR(15 DOWNTO 0);
signal JRopcode, S1opcode, S2opcode : STD_LOGIC_VECTOR(1 DOWNTO 0):="00";

signal isEOR_IDEX : STD_LOGIC:='0';

signal writeAD, RegAD_EXMEM : STD_LOGIC_VECTOR (2 downto 0);

signal wasJump : STD_LOGIC;
signal Flush_hazard, wasJumpOut, isEOR  : STD_LOGIC;

signal IF_Flush							:STD_LOGIC:='0';
signal IF_Enable						:STD_LOGIC:='1';

signal opcode							:STD_LOGIC_VECTOR(3 DOWNTO 0):= outIFID_Instr(15 DOWNTO 12);
signal RD								:STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(11 DOWNTO 9);
signal R1AD								:STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(8 DOWNTO 6);
signal RT								:STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(5 DOWNTO 3);
signal func								:STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(2 DOWNTO 0);
signal immediate						:STD_LOGIC_VECTOR(5 DOWNTO 0):= outIFID_Instr(5 DOWNTO 0);
signal jumpShortAddr					:STD_LOGIC_VECTOR(11 DOWNTO 0):= outIFID_Instr(11 DOWNTO 0);

signal jumpShortAddr_IDEX				:STD_LOGIC_VECTOR(11 DOWNTO 0);

signal R2AD, R2AD_IDEX, R1AD_IDEX   	:STD_LOGIC_VECTOR(2 DOWNTO 0);

signal ALUFunc, ALUFunc_IDEX			:STD_LOGIC_VECTOR(3 DOWNTO 0);
signal allRegOut						:STD_LOGIC_VECTOR(127 DOWNTO 0);

-- CONTROLLER SIGNALS
signal isMFPC, isJump, isReadDigit, isPrintDigit, isR, isLW, isSW, isLW_IDEX, 
       isSW_IDEX, isBranch, isJR, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX,isBranch_IDEX,
		 isR_IDEX, isMFPC_IDEX, isReadDigit_IDEX, isPrintDigit_IDEX,
	    isLW_EXMEM, ReadDigit_EXMEM :STD_LOGIC;

-- ALU SIGNALS
signal ALUInput1, ALUInput2, ALUOutput, Result_EXMEM : STD_LOGIC_VECTOR(15 DOWNTO 0);
signal ALUCarryin, ALUCarryout : STD_LOGIC;
signal ALUOperation : STD_LOGIC_VECTOR(3 DOWNTO 0);

begin

PCReg: PC_REGISTER port map(JROutput, isEOR NOR isEOR_IDEX, clock, PCresult);

IFIDREG: REGISTER_IF_ID PORT map(PCresult, instr, clock, IF_Flush, IF_Enable, outIFID_PC,
                                  outIFID_Instr);

SignExtend: SIGN_EXTENDER port map(immediate, immediate16);

JR: JR_SELECTOR port map(JumpAD, immediate16_IDEX, outIFID_PC, JRopcode, JROutput);

Hazard: HAZARD_UNIT port map(isJR, isJump, wasJump, isBranch_IDEX AND ALUCarryout, Flush_hazard,
									  wasJumpOut, JRopcode);

Trap: TRAP_UNIT port map(opcode, isEOR);

ALUController: ALU_Control port map(opcode, func, ALUFunc);

Controler: CONTROL port map(opcode, func, Flush_hazard OR isEOR_IDEX,
	isMFPC, isJump, isReadDigit, isPrintDigit, isR, isLW, isSW, isBranch, isJR);

selectRegister : process(isR) begin
	case isR is
		when '1'  =>
			R2AD <= RT;
		when '0'  => 
			R2AD <= RD;
		when others => 
			R2AD <= RD;
	end case;
end process;

RegisterFile: REG_FILE port map(clock2, writeData, writeAD, R1AD, R2AD, R1Reg, R2Reg, allRegOut);

IDEXREG: REGISTER_ID_EX port map(clock, isEOR, wasJumpOut, isJump, isJR, isBranch, isR,
        isMFPC, isLW, isSW, isReadDigit, isPrintDigit, ALUFunc, R1Reg , R2Reg , immediate16,
		  R2AD, R1AD, jumpShortAddr, isEOR_IDEX, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX, 
		  isBranch_IDEX, isR_IDEX, isMFPC_IDEX, isLW_IDEX,	isSW_IDEX, isReadDigit_IDEX, 
		  isPrintDigit_IDEX, ALUFunc_IDEX, R1Reg_IDEX , R2Reg_IDEX , immediate16_IDEX, 
			R2AD_IDEX , R1AD_IDEX, jumpShortAddr_IDEX);
																	
Selector1: SELECTOR port map(R1Reg_IDEX, Result_MEMWB, writeData, S1opcode, S1Output);
Selector2: SELECTOR port map(R2Reg_IDEX, Result_MEMWB, writeData, S2opcode, S2Output);

ForwardUnit: FORWARDER port map(R1AD_IDEX, R2AD_IDEX, RegAD_EXMEM, writeAD, S1opcode, S2opcode);

-- ALUSrc, EX_MEM_regwrite, MEM_WB_regwrite : IN STD_LOGIC;

ALUMuxes : process(isMFPC_IDEX, isR_IDEX) begin
	IF (isMFPC_IDEX = '1') then
		ALUInput1 <= S1Output;
	ELSE 
		ALUInput1 <= outIFID_PC;
	END IF;
	IF (isR_IDEX = '1') then
		ALUInput2 <= immediate16_IDEX;
	ELSE 
		ALUInput2 <= S2Output;
	END IF;
end process;

ALU16: ALU_16 port map(ALUInput1, ALUInput2, ALUCarryin, ALUFunc_IDEX, ALUOutput, ALUCarryout);

EXMEMREG: REGISTER_EX_MEM port map(clock, isLW_IDEX, isSW_IDEX, isReadDigit_IDEX, 
							isPrintDigit_IDEX, R2Reg_IDEX, ALUOutput, R2AD_IDEX,
							isLW_EXMEM, DataWriteFlag, ReadDigit_EXMEM, printEnable,
							R2Reg_EXMEM, Result_EXMEM, RegAD_EXMEM);

selectMEMWBResult : process(isLW_EXMEM, ReadDigit_EXMEM) begin
	IF (isLW_EXMEM = '1') then
		Result_MEMWB <= fromData;
	ELSIF (ReadDigit_EXMEM = '1') then
		Result_MEMWB <= keyData;
	ELSE 
		Result_MEMWB <= Result_EXMEM;
	END IF;
end process;

MEMWBREG: REGISTER_MEM_WB port map(Result_MEMWB, RegAD_EXMEM, clock, writeData, writeAD);

-- Behavioral Signals
printData <= Result_EXMEM;

toData <= Result_EXMEM;

printCode <= R2Reg_EXMEM;

keyEnable <= ReadDigit_EXMEM;

dataAD <= R2Reg_EXMEM;

JumpAD <= (15 downto 12 => jumpShortAddr_IDEX(11)) & (jumpShortAddr_IDEX);

instructionAD <= PCresult;

RegOut <=  PCresult & allRegOut;


end structural;