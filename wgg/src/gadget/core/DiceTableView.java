package gadget.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	private int width;
	private int height;
	public DiceTableView(Context context, ArrayList<DiceGroupView> dg, 
			HashMap<Pair,DiceView> lookupDice,int width,int height) {
		super(context);
		diceGroups=dg;
		lookUpDiceTable=lookupDice;
		holder=this.getHolder();
		this.width=width;
		this.height=height;
	}

	@Override
	public void run() {
		while(!disabled){
			if(!holder.getSurface().isValid())continue;
			Canvas c=holder.lockCanvas();
			c.drawARGB(255, 150,150, 0);
			for(DiceGroupView dgv:diceGroups)dgv.draw(c);
			holder.unlockCanvasAndPost(c);
		}
		
	}
	
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		   super.onMeasure(widthMeasureSpec, heightMeasureSpec-80);
	}

	public Object getInteractiveEntity(){
		return null;
		// TODO might need this to look up what has been touched or dragged, etc.
	}
	
	
	
}
	
	
	
