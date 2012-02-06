package gadget.core;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

public class DiceGroupView extends TextView{
	
	private ArrayList<DiceView> diceVs;
	private DiceGroup diceG;
	private int tagRow=-1,tagCol=-1;

	public DiceGroupView(Context context,DiceGroup dg) {
		super(context);
		diceVs= new ArrayList<DiceView>();
		diceG=dg;
	}
	
	public void addDiceView(DiceView dv){
		diceVs.add(dv);
		dv.setParent(this);
	}

	public boolean hasDice() {
		if(diceVs.size()>0)return true;
		return false;
	}

	public DiceView getTopRight() {
		DiceView res=null;
		for(DiceView dv:diceVs){
			if(res==null)res=dv;
			else if(dv.getRow()<res.getRow()){
				res=dv;
			}
			else if(dv.getRow()==res.getRow()&&dv.getColumn()>res.getColumn())
				res=dv;
		}
		return res;
	}

	public int getTagRow() {
		return tagRow;
	}

	public void setTagRow(int tagRow) {
		this.tagRow = tagRow;
	}

	public int getTagCol() {
		return tagCol;
	}

	public void setTagCol(int tagCol) {
		this.tagCol = tagCol;
	}
	
	

}
