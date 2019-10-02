import processing.core.*;
import java.util.*;

public class mainWindow extends PApplet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MutasaurusSystem mutasaurus;
	FoodSystem food;
	public static void main (String args[]) {
		PApplet.main("mainWindow");
	}
	
	public void setup() {
		size(800, 600);
		food = new FoodSystem(this);
		mutasaurus = new MutasaurusSystem(this, 100, food);

	}
	
	public void draw() {
		background(137, 207, 240);
		mutasaurus.run();
		food.run(mutasaurus);
		textSize(20);
		fill(0);
		text("Mutasaurus: "+mutasaurus.getCreatures().size(),14, 30);
	}
}
