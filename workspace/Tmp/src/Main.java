import persoTool.Camera;
import processing.core.PApplet;
import processing.core.PVector;


public class Main extends PApplet{

	private Camera c;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		PApplet.main("Main");
	}



	public void setup() {
		size(640, 360, OPENGL);
		c=new Camera();
	}

	public void draw() {
		this.gereEvent();
		lights();
		background(0);
		// Change height of the camera with mouseY
		PVector p=c.getPosition();
		beginCamera();
		camera();
		translate(p.x, p.y, p.z);
		endCamera();
		
//		camera(p.x, p.y, p.z, // eyeX, eyeY, eyeZ
//				(float)0.0, (float)0.0, (float)0.0, // centerX, centerY, centerZ
//				(float)0.0, (float)1.0, (float)0.0); // upX, upY, upZ
		pushMatrix();
		translate(200, 200, 0);
		noStroke();
		sphere(20);
		stroke(255,0,0);
		line(-100, 0, 0, 100, 0, 0);
		stroke(0,255,0);
		line(0, -100, 0, 0, 100, 0);
		stroke(0,0,255);
		line(0, 0, -100, 0, 0, 100);
		popMatrix();
	}

	
	
	public void gereEvent(){
		if(keyPressed){
			switch (key) {
			case 'z':
				c.add(new PVector(0, 0, 1));
				break;
			case 's':
				c.add(new PVector(0, 0, -1));
				break;
			case 'q':
				c.add(new PVector(1, 0, 0));
				break;
			case 'd':
				c.add(new PVector(-1, 0, 0));
				break;
			case 'a':
				c.add(new PVector(0, 1, 0));
				break;
			case 'e':
				c.add(new PVector(0, -1, 0));
				break;
			default:
				break;
			}
			System.out.println(key);
		}
		if(mousePressed){
			if(mouseButton==39){
				
			}
		}
		
	}
}
