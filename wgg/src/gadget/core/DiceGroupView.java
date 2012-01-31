package gadget.core;

import java.util.ArrayList;

import android.graphics.Canvas;

public class DiceGroupView {
	private ArrayList<DiceView> diceVs;
	private DiceGroup dGroup;
	public DiceGroupView(DiceGroup dg){
		diceVs=new ArrayList<DiceView>();
		dGroup=dg;
	}
	
	public void addDiceV(DiceView dv){
		diceVs.add(dv);
	}

	public void draw(Canvas c) {
		for(DiceView dv:diceVs)dv.draw(c);		
	}

}
