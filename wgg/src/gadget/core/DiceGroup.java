package gadget.core;

import java.util.ArrayList;

public class DiceGroup {
	
	private ArrayList<Dice> dice;
	private int groupSave;
	
	public DiceGroup(){
		dice=new ArrayList<Dice>();
		groupSave=4;
	}
	
	public void addDice(Dice d){
		dice.add(d);
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

}
