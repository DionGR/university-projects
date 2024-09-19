class Point { 
	private int x = 0; 
	private int y = 0; 
	public Point(int x, int y) { 
		this.x = x; 
		this.y = y; 
	} 
}

class Rectangle {
    private int width = 0;
    private int height = 0;
    private Point origin;

    public Rectangle() { Point origin = new Point(0, 0); }
	
    public Rectangle(Point p) { Point origin = p; }

    public Rectangle(int w, int h) {
		this();
		this.width = w;
		this.height = h;
	}

    public Rectangle(Point p, int w, int h) {
		this.origin = p;
		this.width = w;
		this.height = h;
    }

    // a method for computing the area of the rectangle
    public int area() {
		return width * height;
    }
}

public class ex24 {
	public static void main (String args[]) {
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle(new Point(1, 2));
		Rectangle r3 = new Rectangle(3, 4);
		Rectangle r4 = new Rectangle(new Point(5, 6), 7, 8);
	}
}


