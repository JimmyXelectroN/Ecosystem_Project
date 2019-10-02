import java.util.ArrayList;

import processing.core.*;
public interface Creature {
	public void move(ArrayList<food> food);
	public void show();
	public PVector getLocation();
	public float getSize();
	public void addEnergy(float e);
	public float getEnergy();
}
