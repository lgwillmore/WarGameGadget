package gadget.core;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

public class DiceGroupView extends TextView{
	
	private ArrayList<DiceView> diceVs;
	DiceGroup diceG;

	public DiceGroupView(Context context,DiceGroup dg) {
		super(context);
		diceVs= new ArrayList<DiceView>();
		diceG=dg;
	}
	
	public void addDiceView(DiceView dv){
		diceVs.add(dv);
		dv.setParent(this);
	}

}
