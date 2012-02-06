package gadget.core;

import game.core.R;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DiceTableView extends TableLayout implements Runnable{
	
	private ArrayList<DiceGroupView> dgViews;
	private Context context;
	private boolean disabled=false;
	
	
	public DiceTableView(Context context,ArrayList<DiceGroupView> dgv) {
		super(context);
		this.context=context;
		this.dgViews=dgv;
	}

	@Override
	public void run() {
		while(!disabled){
			for(int i=0;i<this.getChildCount();i++){
				
			}
		}
	}
	
	public void positionGroupViewTabs(){
		for(int i=0;i<dgViews.size();i++){
			DiceGroupView dgv=dgViews.get(i);
			if(dgv.hasDice()){
				DiceView dv=dgv.getTopRight();
				if(dgv.getTagRow()!=-1){
					TableRow old=(TableRow)this.getChildAt(dgv.getTagRow());
					old.removeView(dgv);
				}
				TableRow row=(TableRow)this.getChildAt(dv.getRow()*2);
				dgv.setTagRow(dv.getRow()*2);
				dgv.setTagCol(dv.getColumn());
				row.addView(dgv, dv.getColumn());
				//bug here: Need to put place holders in the rows?
			}
		}
	}
	
	
	

}
