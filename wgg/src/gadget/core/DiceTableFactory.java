package gadget.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.SurfaceHolder;

public class DiceTableFactory {
	
	
	int pu;
	int[] ratios = {20,1,2,5,20,4};
	int diceWidth=0,border=1,gap=2,topgap=3,tagwidth=4,taglength=5;
	int topRowspace,leftColspace,midRowspace,midColSpace;
	private static final int WHITE=0,RED=1,BlUE=2,GREEN=3;
	HashMap<Pair,DiceView> diceSlots;
	private SurfaceHolder holder;
	DisplayMetrics screen;
	DiceManager dm;
	Context context;
	ArrayList<DiceGroupView> dgvs;
	

	public DiceTableFactory(Context c,DisplayMetrics screen, DiceManager dm) {
		this.screen=screen;
		this.dm=dm;
		context=c;
	}
	
	public DiceTableView getDiceTableView(){
		calculateSpacing(screen);
		buildDiceViews();
		buildDGVs();
		return new DiceTableView(context,dgvs,diceSlots);
	}
	
	private void buildDGVs() {
		dgvs=new ArrayList<DiceGroupView>();
		for(int i=0;i<5;i++){
			dgvs.add(new DiceGroupView(dm.getDiceGroup(i)));
		}
		for(DiceView dv:diceSlots.values()){
			dgvs.get(WHITE).addDiceV(dv);
		}
	}

	public void calculateSpacing(DisplayMetrics screen){
		//This might be wrong, might need to deal with the actual space available.
		//ie, the space that the view will occupy.
		
		// possibly to solve scaling issue, need to tell the canvas how big it is on the screen?
		int width=screen.widthPixels;
		int height=screen.heightPixels;
		pu= width/144;
		for (int i:ratios)i=i*pu;
		topRowspace=ratios[gap]+2*ratios[border]+ratios[topgap];
		leftColspace=2*ratios[gap]+2*ratios[border];
		midRowspace=ratios[gap]+4*ratios[border]+ratios[topgap]+ratios[diceWidth];
		midColSpace=ratios[diceWidth]+4*ratios[border]+2*ratios[gap];
	}
	
	public void buildDiceViews(){
		diceSlots=new HashMap<Pair, DiceView>();
		int x=0,y=0,row=0;
		while(row<6){
			int col=0;
			if(row==0)y=y+topRowspace;
			else y=y+midRowspace;
			while(col<5){
				if(col==0)x=x+leftColspace;
				else x=x+midColSpace;
				Dice d =new Dice();
				dm.addDice(d);
				diceSlots.put(new Pair(row,col), new DiceView(x,y,ratios[diceWidth],d));
				col++;
			}
			row++;
			x=0;
		}
	}
}


