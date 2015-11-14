import objectdraw.*;

public class Camera extends ActiveObject {

	private final static int DELAY = 20;
	
	private final static Point DEFAULT_POSITION = new Point(500, 500, 500);
	private final static Point DEFAULT_TARGET = new Point(0, 0, 0);
	private final static double DEFAULT_WIDTH = 1280;
	private final static double DEFAULT_HEIGHT = 720;
	private final static double DEFAULT_ZOOM = 1;
	private final static double FOCUS_DISTANCE = 500;
	
	private Point position;
	private Point target;
	private double width;
	private double height;
	private double zoom;
	private double theta = 0;
	
	public Camera() {
		this.position = DEFAULT_POSITION;
		this.target = DEFAULT_TARGET;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.zoom = DEFAULT_ZOOM;
		
		start();
	}
	
	public void run() {
		while (true) {
			position.setPoint(500 * Math.cos(theta), 500 * Math.sin(theta), 200);
			theta += 0.01;
			if (theta >= 2 * Math.PI) {
				theta = 0;
			}
			pause(DELAY);
		}
	}
	
	public Location getLocation(Point point) {
		Vector PP = new Vector(point, position);
		Vector OP = position.toVector();
		if (!PP.isOpposite(OP)) {
			Line line = new Line(point, position);
			Point C = OP.multiply((OP.getLength() - zoom * FOCUS_DISTANCE) / OP.getLength()).toPoint();
			Plane image = new Plane(OP, C);
			Point I = line.getIntersection(image);
			Vector CI = new Vector(C, I);
			
			Vector up = Line.Z_AXIS.parallelTo(image) ? Vector.UNIT_K : new Vector(C, Line.Z_AXIS.getIntersection(image)).getUnitVector();
			
			Vector left = OP.crossProduct(up).getUnitVector();
			
			Vector projY = CI.getProjectionOn(up);
			Vector projX = CI.getProjectionOn(left);
			
			double y = height / 2;
			double x = width / 2;
			if (projY.isOpposite(up)) {
				y += projY.getLength();
			}
			else {
				y -= projY.getLength();
			}
			if (projX.isOpposite(left)) {
				x += projX.getLength();
			}
			else {
				x -= projX.getLength();
			}
			return new Location(x, y);
		}
		else {
			return null;
		}
	}
	
}
