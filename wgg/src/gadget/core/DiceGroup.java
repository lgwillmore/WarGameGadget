package gadget.core;

import java.util.ArrayList;

public class DiceGroup {
	
	private ArrayList<Dice> dice;
	private int groupSave;
	private int colour;
	
	public DiceGroup(int colour){
		dice=new ArrayList<Dice>();
		this.colour=colour;
		groupSave=4;
	}
	
	public void addDice(Dice d){
		dice.add(d);
		d.setParent(this);
	}
	
	public void rollAll(){
		for(Dice d:dice){
			new Thread(d).start();
		}
	}
	
	public boolean isInGroup(Dice d){
		return dice.contains(d);
	}
	
	public void rollDice(Dice d){
		new Thread(d).start();
	}
	
	public void removeDice(Dice d){
		dice.remove(dice.indexOf(d));
	}

	public void setSave(int save) {
		groupSave=save;
	}

	public int getColour() {
		return colour;
	}
	
	

}
