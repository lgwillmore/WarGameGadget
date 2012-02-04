package gadget.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DiceTableView extends SurfaceView implements Runnable{
	
	private ArrayList<DiceGroupView> diceGroups;
	private HashMap<Pair,DiceView> lookUpDiceTable;
	private SurfaceHolder holder;
	private boolean disabled=false;
	
	public DiceTableView(Context context, ArrayList<DiceGroupView> dg, HashMap<Pair,DiceView> lookupDice) {
		super(context);
		diceGroups=dg;
		lookUpDiceTable=lookupDice;
		holder=this.getHolder();
	}

	@Override
	public void run() {
		while(!disabled){
			if(!holder.getSurface().isValid())continue;
			Canvas c=holder.lockCanvas();
			c.drawARGB(255, 0,0, 0);
			for(DiceGroupView dgv:diceGroups)dgv.draw(c);
			holder.unlockCanvasAndPost(c);
		}
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		   super.onMeasure(widthMeasureSpec, heightMeasureSpec-80);
	}

	public Interactible getInteractible(float x,float y){
		for(DiceView dv:lookUpDiceTable.values()){
			if(dv.isMe(x,y))return dv;
		}
		return null;
	}
	
	
	
}
	
	
	
