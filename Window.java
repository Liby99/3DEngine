import objectdraw.*;

public class Window extends WindowController {
	
	private final static int FRAME_WIDTH = 1280;
	private final static int FRAME_HEIGHT = 720;
	
	private Camera camera;
	private Axis axis;
	
	public void begin() {
		camera = new Camera();
		axis = new Axis(camera, canvas);
	}
	
	public static void main(String[] args) {
 		new Acme.MainFrame(new Window(), args, FRAME_WIDTH, FRAME_HEIGHT);
		
//		Camera camera = new Camera();
//		Point p1 = new Point(0, 0, 0);
//		System.out.println(camera.getLocation(p1));
	}
}
