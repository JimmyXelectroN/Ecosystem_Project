import processing.core.*;
import java.util.*;

public class food {
	
	private PVector location;
	private PApplet a;
	private final float calories = 500;
	public food(PApplet app, float x, float y) {
		location = new PVector(x, y);
		a = app;
	}
	
	public void show() {
		a.stroke(1);
		a.fill(0, 255, 0);
		a.ellipse(location.x, location.y, 3, 3);
	}
	
	public boolean collision(ArrayList<Creature> creatures) {
		for(Creature a: creatures) {
			PVector l = a.getLocation();
			float size = a.getSize() - 4;
			if((location.x > l.x - size) && (location.x < l.x + size)) {
				if((location.y > l.y - size) && (location.y < l.y + size)) {
					a.addEnergy(calories);
					return true;
				}
			}
		}
		return false;
	}
	
	public PVector getLocation() {
		return location;
	}
}
