-- Copyright (C) 2019  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and any partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel FPGA IP License Agreement, or other applicable license
-- agreement, including, without limitation, that your use is for
-- the sole purpose of programming logic devices manufactured by
-- Intel and sold by Intel or its authorized distributors.  Please
-- refer to the applicable agreement for further details, at
-- https://fpgasoftware.intel.com/eula.

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 19.1.0 Build 670 09/22/2019 SJ Lite Edition"

-- DATE "05/29/2021 14:02:11"

-- 
-- Device: Altera 5M40ZM64C4 Package MBGA64
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY IEEE;
LIBRARY MAXV;
USE IEEE.STD_LOGIC_1164.ALL;
USE MAXV.MAXV_COMPONENTS.ALL;

ENTITY 	ALU IS
    PORT (
	a : IN std_logic;
	b : IN std_logic;
	CarryIn : IN std_logic;
	AInvert : IN std_logic;
	BInvert : IN std_logic;
	Operation : IN std_logic_vector(1 DOWNTO 0);
	CarryOut : OUT std_logic;
	Result : OUT std_logic
	);
END ALU;

-- Design Ports Information


ARCHITECTURE structure OF ALU IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_a : std_logic;
SIGNAL ww_b : std_logic;
SIGNAL ww_CarryIn : std_logic;
SIGNAL ww_AInvert : std_logic;
SIGNAL ww_BInvert : std_logic;
SIGNAL ww_Operation : std_logic_vector(1 DOWNTO 0);
SIGNAL ww_CarryOut : std_logic;
SIGNAL ww_Result : std_logic;
SIGNAL \a~combout\ : std_logic;
SIGNAL \b~combout\ : std_logic;
SIGNAL \BInvert~combout\ : std_logic;
SIGNAL \BInverter|invertRes~0_combout\ : std_logic;
SIGNAL \CarryIn~combout\ : std_logic;
SIGNAL \AInvert~combout\ : std_logic;
SIGNAL \fullAdd|Cout~0_combout\ : std_logic;
SIGNAL \AInverter|invertRes~0_combout\ : std_logic;
SIGNAL \Final|Mux0~0_combout\ : std_logic;
SIGNAL \XOR_2|xorRes~0_combout\ : std_logic;
SIGNAL \Final|Mux0~1_combout\ : std_logic;
SIGNAL \Operation~combout\ : std_logic_vector(1 DOWNTO 0);

BEGIN

ww_a <= a;
ww_b <= b;
ww_CarryIn <= CarryIn;
ww_AInvert <= AInvert;
ww_BInvert <= BInvert;
ww_Operation <= Operation;
CarryOut <= ww_CarryOut;
Result <= ww_Result;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

-- Location: PIN_F5,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\a~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_a,
	combout => \a~combout\);

-- Location: PIN_D8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\b~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_b,
	combout => \b~combout\);

-- Location: PIN_B7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\BInvert~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_BInvert,
	combout => \BInvert~combout\);

-- Location: LC_X5_Y2_N9
\BInverter|invertRes~0\ : maxv_lcell
-- Equation(s):
-- \BInverter|invertRes~0_combout\ = ((\b~combout\ $ (\BInvert~combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0ff0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datac => \b~combout\,
	datad => \BInvert~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \BInverter|invertRes~0_combout\);

-- Location: PIN_G7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\CarryIn~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_CarryIn,
	combout => \CarryIn~combout\);

-- Location: PIN_F8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\AInvert~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_AInvert,
	combout => \AInvert~combout\);

-- Location: LC_X5_Y2_N4
\fullAdd|Cout~0\ : maxv_lcell
-- Equation(s):
-- \fullAdd|Cout~0_combout\ = (\BInverter|invertRes~0_combout\ & ((\CarryIn~combout\) # (\a~combout\ $ (\AInvert~combout\)))) # (!\BInverter|invertRes~0_combout\ & (\CarryIn~combout\ & (\a~combout\ $ (\AInvert~combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d4e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \a~combout\,
	datab => \BInverter|invertRes~0_combout\,
	datac => \CarryIn~combout\,
	datad => \AInvert~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \fullAdd|Cout~0_combout\);

-- Location: PIN_C8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\Operation[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_Operation(0),
	combout => \Operation~combout\(0));

-- Location: LC_X5_Y2_N5
\AInverter|invertRes~0\ : maxv_lcell
-- Equation(s):
-- \AInverter|invertRes~0_combout\ = (\a~combout\ $ (((\AInvert~combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "33cc",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \a~combout\,
	datad => \AInvert~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \AInverter|invertRes~0_combout\);

-- Location: PIN_F6,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\Operation[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_Operation(1),
	combout => \Operation~combout\(1));

-- Location: LC_X5_Y2_N2
\Final|Mux0~0\ : maxv_lcell
-- Equation(s):
-- \Final|Mux0~0_combout\ = (\Operation~combout\(0) & ((\BInverter|invertRes~0_combout\) # ((\AInverter|invertRes~0_combout\) # (\Operation~combout\(1))))) # (!\Operation~combout\(0) & (\BInverter|invertRes~0_combout\ & (\AInverter|invertRes~0_combout\ & 
-- !\Operation~combout\(1))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "aae8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \Operation~combout\(0),
	datab => \BInverter|invertRes~0_combout\,
	datac => \AInverter|invertRes~0_combout\,
	datad => \Operation~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \Final|Mux0~0_combout\);

-- Location: LC_X5_Y2_N6
\XOR_2|xorRes~0\ : maxv_lcell
-- Equation(s):
-- \XOR_2|xorRes~0_combout\ = \BInvert~combout\ $ (\a~combout\ $ (\b~combout\ $ (\AInvert~combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "6996",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \BInvert~combout\,
	datab => \a~combout\,
	datac => \b~combout\,
	datad => \AInvert~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \XOR_2|xorRes~0_combout\);

-- Location: LC_X5_Y2_N8
\Final|Mux0~1\ : maxv_lcell
-- Equation(s):
-- \Final|Mux0~1_combout\ = (\Operation~combout\(1) & (\XOR_2|xorRes~0_combout\ $ (((\CarryIn~combout\ & !\Final|Mux0~0_combout\))))) # (!\Operation~combout\(1) & (((\Final|Mux0~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d2cc",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \CarryIn~combout\,
	datab => \Final|Mux0~0_combout\,
	datac => \XOR_2|xorRes~0_combout\,
	datad => \Operation~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \Final|Mux0~1_combout\);

-- Location: PIN_A6,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\CarryOut~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \fullAdd|Cout~0_combout\,
	oe => VCC,
	padio => ww_CarryOut);

-- Location: PIN_H7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\Result~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \Final|Mux0~1_combout\,
	oe => VCC,
	padio => ww_Result);
END structure;


