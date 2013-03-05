import shapes3d.utils.*;
import shapes3d.*;

import processing.core.PApplet;
import processing.opengl.*;


public class Test extends PApplet{

	/**
	Simple program to demonstrate the shape picking feature
	of this library.
	Click on the slowly revolving cubes to change their colour.

	This demo uses the off-screen buffer version of the pick
	shape algorithm.

	created by Peter Lager 2010
	 */

	Box[] box = new Box[20];
	float a;

	public void setup(){
		size(200,200,OPENGL);
		cursor(CROSS);
		float size;
		for (int i = 0; i < box.length; i++) {
			size = 5 + (int)random(10);
			box[i] = new Box(this,size,size,size);
			box[i].moveTo(random(-22,22), random(-22,22), random(-22,22));
			box[i].fill(randomColor());
			box[i].stroke(color(64,0,64));
			box[i].strokeWeight((float) 1.2);
			box[i].drawMode(Shape3D.SOLID | Shape3D.WIRE);
		}
	}

	public void draw(){
		background(128);
		pushMatrix();
		camera(70 * sin(a), 10, 70 * cos(a), 0, 0, 0, 0, 1, 0);
		ambientLight(200,200,200);
		directionalLight(128, 128, 128, -1, 0, -1);

		for (int i = 0; i < box.length; i++) {
			box[i].draw();
		}
		popMatrix();
		a += 0.002;
	}

	public void mouseClicked(){
		Shape3D picked = Shape3D.pickShape(((PApplet)(this)),mouseX, mouseY);
		System.out.println(picked);
		if(picked != null)
			picked.fill(randomColor());
	}

	public int randomColor(){
		return color(random(160,200), random(20,160), random(160,200));
	}
}
