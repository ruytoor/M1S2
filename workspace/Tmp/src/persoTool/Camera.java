package persoTool;

import processing.core.PVector;



public class Camera {

	/**
	 * @param args
	 */
	private PVector position;
	private Quaternion rotation;

	public Camera(){
		position=new PVector(0, 0, 0);
		rotation=new Quaternion();
	}

	public PVector getPosition() {
		return position;
	}

	public void add(PVector p){
		position.add(p.x, p.y, p.z);
	}

}
