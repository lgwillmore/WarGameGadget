package gadget.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TableLayout;
import android.widget.TableRow;


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
		while(true){
			if(!holder.getSurface().isValid())continue;
			Canvas c=holder.lockCanvas();
			c.drawARGB(255, 150,150, 0);
			for(DiceGroupView dgv:diceGroups)dgv.draw(c);
			holder.unlockCanvasAndPost(c);
		}
		
	}
	
	public Object getInteractiveEntity(){
		return null;
		// TODO might need this to look up what has been touched or dragged, etc.
	}
	
	
	
}
	
	
	
