
class ClassA {
	
    public void methodOne() {
		System.out.println ("Class A methodOne");
    }
	
    public static void methodTwo() {
		System.out.println ("Class A methodTwo");
    }
	
    public void methodThree() {
		System.out.println ("Class A methodThree");
    }
}

class ClassB extends ClassA {
	
    public void methodOne() {
		System.out.println ("Class B methodOne");
    }
	
    public static void methodTwo() {
		System.out.println ("Class B methodTwo");
    }

}

public class ex22{
	
	public static void main(String args[]){
		ClassB b = new ClassB();
		b.methodOne();
		b.methodTwo();
		b.methodThree();
			
	}
	
}
