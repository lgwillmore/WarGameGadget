package gadget.core;

import java.util.ArrayList;

public class DiceManager {
	private ArrayList<DiceGroup> dgs;
	private static final int WHITE=0,RED=1,BlUE=2,GREEN=3;
	private int globalSave;
	
	public DiceManager(){
		dgs=new ArrayList<DiceGroup>();
		for(int i=0;i<5;i++){
			dgs.add(new DiceGroup(i));
		}
	}
	
	public void addDice(Dice d){
		dgs.get(WHITE).addDice(d);
	}
	
	public void rollAll(){
		for(DiceGroup dg:dgs){
			dg.rollAll();
		}
	}
	
	public void rollGroup(int colour){
		dgs.get(colour).rollAll();
	}
	
	public void moveDice(Dice d, int from, int to){
		dgs.get(from).removeDice(d);
		dgs.get(to).addDice(d);
	}
	
	public boolean saveIsDif(int compSave){
		return !(compSave==globalSave);
	}
	
	public void setGlobalSave(int save){
		globalSave=save;
	}
	
	public void setAllSaves(int save){
		globalSave=save;
		for(DiceGroup dg:dgs){
			dg.setSave(save);
		}
	}
	
	public void setGroupSave(int GroupColour, int save){
		dgs.get(GroupColour).setSave(save);
	}
	
	public DiceGroup getDiceGroup(int Colour){
		return dgs.get(Colour);
	}
}
