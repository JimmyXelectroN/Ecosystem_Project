import processing.core.*;
import java.util.*;

public class FoodSystem {
	ArrayList<food> food;
	PApplet applet;
	Random r = new Random();
	public FoodSystem(PApplet app) {
		food = new ArrayList<food>();
		applet = app;
	}
	public void run(MutasaurusSystem mutasaurus) {
		float foodSpawnProb = r.nextFloat();
		if(foodSpawnProb < 0.5) {
			float x = r.nextInt(applet.width);
			float y = r.nextInt(applet.width);
			food f = new food(applet,x,y);
			food.add(f);
		}
		for(food f: food) {
			f.show();
		}
		for(int i=0;i < food.size(); i++) {
			if(food.get(i).collision(mutasaurus)) {
				food.remove(food.get(i));
			}
		}
	}
	
	public ArrayList<food> getFood(){
		return food;
	}
}
