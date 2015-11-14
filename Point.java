
public class Point {
	
	public final static Point ORIGIN = new Point(0, 0, 0);
	
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setPoint(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector toVector() {
		return new Vector(x, y, z);
	}
	
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
	
	public boolean equals(Point point) {
		return x == point.getX() && y == point.getY() && z == point.getZ();
	}
}
