
public class Vector {
	
	public final static Vector ZERO = new Vector(0, 0, 0);
	public final static Vector UNIT_I = new Vector(1, 0, 0);
	public final static Vector UNIT_J = new Vector(0, 1, 0);
	public final static Vector UNIT_K = new Vector(0, 0, 1);
	
	private double x;
	private double y;
	private double z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(Point point) {
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}
	
	public Vector(Point start, Point end) {
		this.x = end.getX() - start.getX();
		this.y = end.getY() - start.getY();
		this.z = end.getZ() - start.getZ();
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
	
	public Vector add(Vector vector) {
		return new Vector(x + vector.x, y + vector.y, z + vector.z);
	}
	
	public Vector minus(Vector vector) {
		return new Vector(x - vector.x, y - vector.y, z - vector.z);
	}
	
	public Vector multiply(double constant) {
		return new Vector(x * constant, y * constant, z * constant);
	}
	
	public Vector inverse() {
		return new Vector(-x, -y, -z);
	}
	
	public double getLength() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector getUnitVector() {
		double l = getLength();
		return new Vector(x / l, y / l, z / l);
	}
	
	public Line getLine() {
		return new Line(ZERO, this);
	}
	
	public double dotProduct(Vector vector) {
		return x * vector.x + y * vector.y + z * vector.z;
	}
	
	public Vector crossProduct(Vector vector) {
		double px = y * vector.z - z * vector.y;
		double py = z * vector.x - x * vector.z;
		double pz = x * vector.y - y * vector.x;
		return new Vector(px, py, pz);
	}
	
	public double getCosBetween(Vector vector) {
		return dotProduct(vector) / (getLength() * vector.getLength());
	}
	
	public double getAngularBetween(Vector vector) {
		return Math.acos(getCosBetween(vector));
	}
	
	public double getDegreeBetween(Vector vector) {
		return getAngularBetween(vector) / Math.PI * 180;
	}
	
	public Vector getProjectionOn(Vector vector) {
		double cp = dotProduct(vector);
		double ls = Math.pow(vector.getUnitVector().getLength(), 2);
		return vector.multiply(cp / ls);
	}
	
	public Vector getProjectionOn(Line line) {
		return getProjectionOn(line.getDirection());
	}
	
	public boolean parallelTo(Vector vector) {
		return crossProduct(vector).isZeroVector();
	}
	
	public boolean parallelTo(Line line) {
		return parallelTo(line.getDirection());
	}
	
	public boolean perpendicularTo(Vector vector) {
		return dotProduct(vector) == 0;
	}
	
	public boolean perpendicularTo(Line line) {
		return perpendicularTo(line.getDirection());
	}
	
	public Plane getNormalPlane() {
		return new Plane(x, y, z);
	}
	
	public boolean isOpposite(Vector vector) {
		return getCosBetween(vector) < 0;
	}
	
	public boolean isZeroVector() {
		return equals(ZERO);
	}
	
	public boolean normalized() {
		return getLength() == 1;
	}
	
	public boolean equals(Vector vector) {
		return x == vector.x && y == vector.y && z == vector.z;
	}
	
	public Point toPoint() {
		return new Point(x, y, z);
	}
	
	public String toString() {
		return "<" + x + "," + y + "," + z + ">";
	}
	
}
