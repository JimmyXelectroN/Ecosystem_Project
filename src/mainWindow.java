import processing.core.*;
import java.util.*;

public class mainWindow extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<food> food;
	ArrayList<Creature> creatures;
	Random r = new Random();
	ArrayList<Creature> dead;
	ArrayList<Creature> born;
	float n;
	public static void main (String args[]) {
		PApplet.main("mainWindow");
	}
	
	public void setup() {
		size(800, 600);
		float tx = 0;
		float ty = 10000;
		creatures = new ArrayList<Creature>();
		for(int i=0; i < 1000; i++) {
			float n = (float) r.nextGaussian();
			float size = Math.abs((n*2)-25);
			Creature a = new Mutasaurus(this, r.nextInt(width), r.nextInt(height), size);
			System.out.println(size);
			creatures.add(a);
		}
		food = new ArrayList<food>();

	}
	
	public void draw() {
		background(137, 207, 240);
		float foodSpawnProb = r.nextFloat();
		dead = new ArrayList<Creature>();
		born = new ArrayList<Creature>();
		if(foodSpawnProb < 0.5) {
			float x = r.nextFloat() * width;
			float y = r.nextFloat() * height;
			food f = new food(this,x,y);
			food.add(f);
		}
		for(food f: food) {
			f.show();
		}
		for(Creature a: creatures) {
			if (a.getEnergy() > 0) {
				if (a.getEnergy() > 10000) {
					float size = Math.abs((n*2)-10);
					Creature cr = new Mutasaurus(this, a.getLocation().x, a.getLocation().y, size);
					born.add(cr);
					a.addEnergy(-5000);
				}
				pushMatrix();
				a.show();
				a.move(food);
				popMatrix();
			}
			else {
				dead.add(a);
			}
			
		}
		for(Creature c: born) {
			creatures.add(c);
		}
		for(Creature c: dead) {
			creatures.remove(c);
		}
		for(int i=0;i < food.size(); i++) {
			if(food.get(i).collision(creatures)) {
				food.remove(food.get(i));
			}
		}
		textSize(20);
		fill(0);
		text("Mutasaurus: "+creatures.size(),14, 30);
	}
}
