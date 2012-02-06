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
		
	}
	
	
	

}
