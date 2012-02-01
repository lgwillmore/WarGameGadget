package gadget.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.SurfaceHolder;

public class DiceTableFactory {
	
	
	int pu;
	int tvheight;
	int tvwidth;
	int[] ratios = {20,1,2,5,20,4};
	int diceWidth=0,border=1,gap=2,topgap=3,tagwidth=4,taglength=5;
	int topRowspace,leftColspace,midRowspace,midColSpace;
	private static final int WHITE=0,RED=1,BlUE=2,GREEN=3;
	private HashMap<Pair,DiceView> diceSlots;
	private ArrayList<ArrayList<Bitmap>> diceFaces;
	private SurfaceHolder holder;
	DisplayMetrics screen;
	DiceManager dm;
	Context context;
	ArrayList<DiceGroupView> dgvs;
	

	public DiceTableFactory(Context c,DisplayMetrics screen, DiceManager dm,
			ArrayList<ArrayList<Bitmap>> diceFaces) {
		this.screen=screen;
		this.dm=dm;
		context=c;
		this.diceFaces=diceFaces;
	}
	
	public DiceTableView getDiceTableView(){
		calculateSpacing(screen);
		resizeBitmaps();
		buildDiceViews();
		buildDGVs();		
		return new DiceTableView(context,dgvs,diceSlots,tvwidth,tvheight);
	}
	
	private void resizeBitmaps(){
		for(ArrayList<Bitmap> colour:diceFaces){
			for(int i=0;i<colour.size();i++){
				Bitmap rSize=Util.resizeBitmap(colour.get(i), ratios[diceWidth], ratios[diceWidth]);
				colour.set(i, rSize);
			}
		}
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
		
		int width=screen.widthPixels;
		tvwidth=width;
		int height=screen.heightPixels;
		pu= width/144;
		int rem=width%144;
		int addtoDice=rem/5;
		for (int i=0;i<6;i++){
			ratios[i]=ratios[i]*pu;
		}
		ratios[diceWidth]=ratios[diceWidth]+addtoDice;
		topRowspace=ratios[gap]+2*ratios[border]+ratios[topgap];
		leftColspace=2*ratios[gap]+2*ratios[border];
		midRowspace=ratios[gap]+4*ratios[border]+ratios[topgap]+ratios[diceWidth];
		midColSpace=ratios[diceWidth]+4*ratios[border]+2*ratios[gap];
	}
	
	public void buildDiceViews(){
		DiceView.setDiceFaces(diceFaces);
		diceSlots=new HashMap<Pair, DiceView>();
		int x=0,y=0,row=0;
		while(row<6){
			int col=0;
			if(row==0)y=y+topRowspace;
			else y=y+midRowspace;
			while(col<5){
				if(col==0)x=x+leftColspace;
				else x=x+midColSpace;
				Dice d =new Dice(0);
				dm.addDice(d);
				diceSlots.put(new Pair(row,col), new DiceView(x,y,ratios[diceWidth],d));
				col++;
			}
			row++;
			x=0;
		}
		tvheight=y+midRowspace+pu;
	}
}


