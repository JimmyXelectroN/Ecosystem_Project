import processing.core.*;
import java.util.*;

public class Mutasaurus implements Creature{
	private PVector location;
	private PVector velocity;
	private PVector acceleration;
	private PApplet a;
	private Random r = new Random();
	private final float view = 100;
	private float s;
	private PVector randMove = PVector.random2D();
	private float energy = 7500;
	
	public Mutasaurus(PApplet app, float x, float y, float size) {
		a = app;
		location = new PVector(x,y);
		velocity = new PVector(0,0);
		acceleration = new PVector(0,0);
		s = size*0.3f;
	}
	
	@Override
	public void move(ArrayList<food> food) {
		// TODO Auto-generated method stub
		ArrayList<food> viewable = new ArrayList<food>();
		for(food f: food) {
			PVector pos = new PVector(f.getLocation().x, f.getLocation().y);
			pos.sub(location);
			float length = pos.mag();
			if(length < view) {
				viewable.add(f);
			}
		}
		
		if(viewable.size() > 0) {
			food closest = getClosest(viewable);
			PVector pos = new PVector(closest.getLocation().x, closest.getLocation().y);
			pos.sub(location);
			pos.normalize();
			pos.mult(0.8f);
			acceleration = pos;
			velocity.add(acceleration);
			energy -= velocity.mag()*3;
			location.add(velocity);
			velocity.limit(2);
			
			if (location.x > a.width) {
				location.x = 0;
			}
			if (location.x < 0) {
				location.x = a.width;
			}
			if (location.y > a.height) {
				location.y = 0;
			}
			if (location.y < 0) {
				location.y = a.height;
			}
		}
		
		else {
			acceleration = randMove;
			acceleration.normalize();
			acceleration.mult(0.000001f);
			velocity.add(acceleration);
			energy -= 3;
			location.add(velocity);
			velocity.limit(2);
			
			if (location.x > a.width) {
				location.x = 0;
			}
			if (location.x < 0) {
				location.x = a.width;
			}
			if (location.y > a.height) {
				location.y = 0;
			}
			if (location.y < 0) {
				location.y = a.height;
			}
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		a.stroke(1);
		a.pushMatrix();
		a.fill(255, 0, 0);
		a.translate(location.x, location.y);
		float angle = PApplet.atan2(velocity.y,velocity.x);
		a.rotate(angle);
		a.rectMode(PConstants.CENTER);
		a.rect(0, 0, s, s);
		a.popMatrix();
		
	}
	
	@Override
	public PVector getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	
	private food getClosest(ArrayList<food> foods) {
		food closest = foods.get(r.nextInt(foods.size()));
		PVector posC = new PVector(closest.getLocation().x, closest.getLocation().y);
		float closestL = posC.mag();
		
		for(food f: foods) {
			PVector pos = new PVector(f.getLocation().x, f.getLocation().y);
			pos.sub(location);
			float length = pos.mag();
			if(length < view) {
				if(length < closestL) {
					closest = f;
					closestL = length;
				}
			}
		}
		
		return closest;
	}
	@Override
	public float getSize() {
		return s;
	}

	@Override
	public void addEnergy(float e) {
		// TODO Auto-generated method stub
		energy += e;
	}
	
	@Override
	public float getEnergy() {
		return energy;
	}
}
