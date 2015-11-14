
public class Plane {
	
	public final static Plane XY_PLANE = new Plane(Vector.UNIT_I, Vector.UNIT_J);
	public final static Plane XZ_PLANE = new Plane(Vector.UNIT_I, Vector.UNIT_K);
	public final static Plane YZ_PLANE = new Plane(Vector.UNIT_J, Vector.UNIT_K);
	
	private double a;
	private double b;
	private double c;
	private double d;
	
	public Plane(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = 0;
	}
	
	public Plane(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	public Plane(Point p1, Point p2, Point p3) {
		Vector v1 = new Vector(p1, p2);
		Vector v2 = new Vector(p1, p3);
		Vector cp = v1.crossProduct(v2);
		a = cp.getX();
		b = cp.getY();
		c = cp.getZ();
		d = a * p1.getX() + b * p1.getY() + c * p1.getZ();
	}
	
	public Plane(Vector v1, Vector v2) {
		Vector cp = v1.crossProduct(v2);
		a = cp.getX();
		b = cp.getY();
		c = cp.getZ();
		d = 0;
	}
	
	public Plane(Vector normal, Point point) {
		a = normal.getX();
		b = normal.getY();
		c = normal.getZ();
		d = a * point.getX() + b * point.getY() + c * point.getZ();
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getC() {
		return c;
	}
	
	public double getD() {
		return d;
	}
	
	public Vector getNormalVector() {
		return new Vector(a, b, c);
	}
	
	public boolean equals(Plane plane) {
		return parallelTo(plane) && plane.contains(getNormalVector().getLine().getIntersection(this));
	}
	
	public boolean contains(Point point) {
		return d == a * point.getX() + b * point.getY() + c * point.getZ();
	}
	
	public boolean contains(Line line) {
		return contains(line.getPoint(0)) && contains(line.getPoint(1));
	}
	
	public boolean parallelTo(Plane plane) {
		return getNormalVector().parallelTo(plane.getNormalVector());
	}
	
	public String toString() {
		return a + " * x + " + b + " * y + " + c + " * z = " + d;
	}
	
	public static boolean inSamePlane(Point p1, Point p2, Point p3, Point p4) {
		Plane plane = new Plane(p1, p2, p3);
		return plane.contains(p4);
	}
	
}
