import java.util.Random;   
import java.util.Scanner;

class Card {
	Random rg; //A random number generator, used to get the digits for the card number.
	int[] number;  //The card number.
	public Card() {
		rg = new Random();  
	}
	public long getNumber()	{
		String temp = "";
		for(int i = 0; i < number.length; i++) temp += number[i];
		return Long.parseLong(temp);
	}
	//Validates a credit card number using Luhn's formula.
	public static boolean validate(int[] number) {
		int sum = 0;
    	int length = number.length;
     	for (int i = 0; i < length; i++) {
       		int digit = number[length - i - 1];
       		if (i % 2 == 1) digit *= 2;
       		sum += digit > 9 ? digit - 9 : digit;
     	}
     	return sum % 10 == 0;
	}
}

//-------------------------------------------------------
class Visa extends Card
{
	public Visa(int digits) {
		super();
		number = new int[digits];
		for (int i = 0; i < digits; i++) {
			number[i] = (i == 0) ? 4 : rg.nextInt(9);
		}
	}
}

// ---------------------------------------------------
class MasterCard extends Card
{
	public MasterCard()
	{
		super();
		number = new int[] {5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i = 2; i < 16; i++) {
			number[i] = rg.nextInt(9);
		}
	}
}

//-------------------------------------------------

class Diners extends Card
{
	public Diners()
	{
		super();
		number = new int[] {3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i = 2; i < 14; i++) {
			number[i] = rg.nextInt(9);
		}
	}
}

//----------------------------------------------------

class AmericanExpress extends Card
{
	public AmericanExpress()
	{
		super();
		number = new int[15];
		for(int i = 0; i < 15; i++) {
			number[i] = (i == 0) ? 3 : rg.nextInt(9);
		}
	}
}


public class ex29 {
	
	public static void main (String args[]){
		Scanner in = new Scanner(System.in);
		System.out.println ("Which card type do you need? (1 = Visa, 2 = MasterCard, 3 = American Express, 4 = Diners)");
		int type = in.nextInt();
	
		if(type == 1) {
			System.out.println ("How many digits for the Visa card? (13 or 16)");
			int digits = in.nextInt();
			Visa v = new Visa(digits);
			while(!Card.validate(v.number))	v = new Visa(digits);
			System.out.println("Visa Number: " + v.getNumber());
		}
		else if(type == 2) {
			MasterCard mc = new MasterCard();
			while(!Card.validate(mc.number)) mc = new MasterCard();
			System.out.println("MasterCard Number: " + mc.getNumber());
		}
		else if(type == 3) {
			AmericanExpress ae = new AmericanExpress();
			while(!Card.validate(ae.number)) ae = new AmericanExpress();
			System.out.println("American Express Number: " + ae.getNumber());
		}
		else if(type == 4) {
			Diners d = new Diners();
			while(!Card.validate(d.number)) d = new Diners();
			System.out.println("Diners Number: " + d.getNumber());
		}
		else {
			System.out.println("Error! Card type not recognised!");
		}		
	}// main
}// ex9