import processing.core.*;
import java.util.*;

public class MutasaurusSystem {
	ArrayList<Creature> creatures;
	Random r = new Random();
	ArrayList<Creature> dead;
	ArrayList<Creature> born;
	PApplet applet;
	FoodSystem food;
	float n = (float) r.nextGaussian();
	public MutasaurusSystem(PApplet app, int numOfCreatures, FoodSystem foodList) {
		applet = app;
		food = foodList;
		creatures = new ArrayList<Creature>();
		for(int i=0; i < numOfCreatures; i++) {
			float num = (float) r.nextGaussian();
			float size = Math.abs((num*2)-25);
			Creature a = new Mutasaurus(applet, r.nextInt(applet.width), r.nextInt(applet.height), size);
			creatures.add(a);
		}
	}
	
	public void run() {
		dead = new ArrayList<Creature>();
		born = new ArrayList<Creature>();
		
		for(Creature cr: creatures) {
			if (cr.getEnergy() > 0) {
				if (cr.getEnergy() > 10000) {
					float size = Math.abs((n*2)-10);
					Creature child = new Mutasaurus(applet, applet.getLocation().x, applet.getLocation().y, size);
					born.add(child);
					cr.addEnergy(-5000);
				}
				applet.pushMatrix();
				cr.show();
				cr.move(food.getFood());
				applet.popMatrix();
			}
			else {
				dead.add(cr);
			}
			
		}
		for(Creature c: born) {
			creatures.add(c);
		}
		for(Creature c: dead) {
			creatures.remove(c);
		}
	}
	
	public ArrayList<Creature> getCreatures(){
		return creatures;
	}

}
