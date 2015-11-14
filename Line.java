
public class Line {
	
	public final static Line X_AXIS = new Line(Vector.ZERO, Vector.UNIT_I);
	public final static Line Y_AXIS = new Line(Vector.ZERO, Vector.UNIT_J);
	public final static Line Z_AXIS = new Line(Vector.ZERO, Vector.UNIT_K);
	
	private Vector start;
	private Vector direction;
	
	public Line(Point p1, Point p2) {
		start = p1.toVector();
		direction = p2.toVector().minus(start);
	}
	
	public Line(Vector start, Vector direction) {
		this.start = start;
		this.direction = direction;
	}
	
	public Vector getStart() {
		return start;
	}
	
	public Vector getDirection() {
		return direction;
	}
	
	public Point getPoint(double t) {
		return start.add(direction.multiply(t)).toPoint();
	}
	
	public boolean intersects(Line line) {
		return Plane.inSamePlane(getPoint(0), getPoint(1), line.getPoint(0), line.getPoint(1)) && 
			   !parallelTo(line);
	}
	
	public boolean intersects(Plane plane) {
		return !parallelTo(plane);
	}
	
	public Point getIntersection(Line line) {
		if (intersects(line)) {
      double temp1 = line.start.minus(start).crossProduct(direction).getZ();
    		double temp2 = direction.crossProduct(line.direction).getZ();
    		double t = temp1 / temp2;
    		return line.getPoint(t);
		}
		else {
			return null;
		}
	}
	
	public Point getIntersection(Plane plane) {
		if (intersects(plane)) {
			  double temp1 = plane.getD() - plane.getA() * start.getX() - plane.getB() * start.getY() - plane.getC() * start.getZ();
    		double temp2 = plane.getA() * direction.getX() + plane.getB() * direction.getY() + plane.getC() * direction.getZ();
    		double t = temp1 / temp2;
    		return getPoint(t);
		}
		else {
			return null;
		}
	}
	
	public boolean parallelTo(Line line) {
		return direction.parallelTo(line);
	}
	
	public boolean parallelTo(Plane plane) {
		return direction.perpendicularTo(plane.getNormalVector());
	}
	
	public boolean perpendicularTo(Line line) {
		return direction.perpendicularTo(line.direction);
	}
	
	public boolean perpendicularTo(Plane plane) {
		return direction.parallelTo(plane.getNormalVector());
	}
	
	public boolean contains(Point point) {
		return point.toVector().minus(start).parallelTo(direction);
	}
	
	public boolean equals(Line line) {
		return line.contains(start.toPoint()) && direction.parallelTo(line.direction);
	}
	
	public String toString() {
		return start + " + t * " + direction;
	}
}
