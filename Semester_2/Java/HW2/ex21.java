class Rectangle {
	
	int width;
	int height;
	int area(){
		return width * height;
	}
}

 public class ex21 {
	 
    public static void main(String[] args) {
		
        Rectangle myRect = new Rectangle();
        myRect.width = 4;
        myRect.height = 5;
        System.out.println("myRect's area is " + myRect.area());
    }
}
