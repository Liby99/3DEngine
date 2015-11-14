import objectdraw.*;

public class Axis extends ActiveObject {
	
	private final static int DELAY = 20;
	
	private Camera camera;
	private DrawingCanvas canvas;
	private objectdraw.Line xAxis;
	private objectdraw.Line yAxis;
	private objectdraw.Line zAxis;
	private Point origin = Point.ORIGIN;
	private Point x = new Point(500, 0, 0);
	private Point y = new Point(0, 500, 0);
	private Point z = new Point(0, 0, 500);
	
	private FilledRect[][] grid = new FilledRect[19][19];

	public Axis(Camera camera, DrawingCanvas canvas) {
		this.canvas = canvas;
		this.camera = camera;
		
		Location o = camera.getLocation(origin);
		Location x1 = camera.getLocation(this.x);
		Location y1 = camera.getLocation(this.y);
		Location z1 = camera.getLocation(this.z);
		
		xAxis = new objectdraw.Line(o, x1, canvas);
		yAxis = new objectdraw.Line(o, y1, canvas);
		zAxis = new objectdraw.Line(o, z1, canvas);
		
		drawGrid();

		start();
	}

	public void drawGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Location loc = camera.getLocation(new Point(-900 + i * 100, -900 + j * 100, 0));
				if (loc != null)
					grid[i][j] = new FilledRect(loc, 2, 2, canvas);
			}
		}
	}

	public void setGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Location loc = camera.getLocation(new Point(-900 + i * 100, -900 + j * 100, 0));
				if (loc != null) {
					if (grid[i][j] != null) {
						grid[i][j].show();
						grid[i][j].moveTo(loc);
					}
					else {
						grid[i][j] = new FilledRect(loc, 2, 2, canvas);
					}
				}
				else {
					if (grid[i][j] != null) {
						grid[i][j].hide();
					}
					else {
						
					}
				}
			}
		}
	}
	
	public void run() {
		while (true) {

			Location o = camera.getLocation(origin);
			Location x1 = camera.getLocation(this.x);
			Location y1 = camera.getLocation(this.y);
			Location z1 = camera.getLocation(this.z);
			xAxis.setEndPoints(o, x1);
			yAxis.setEndPoints(o, y1);
			zAxis.setEndPoints(o, z1);

			setGrid();
			
			pause(DELAY);
		}
	}
}
