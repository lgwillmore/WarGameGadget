package gadget.core;

import game.core.R;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DiceTableView extends TableLayout implements Runnable{
	
	private ArrayList<DiceGroupView> dgViews;
	private ArrayList<Pair<Integer,Integer>> dgVLocs;
	private Context context;
	private boolean disabled=false;
	
	
	public DiceTableView(Context context,ArrayList<DiceGroupView> dgv) {
		super(context);
		this.context=context;
		this.dgViews=dgv;
		dgVLocs=new ArrayList<Pair<Integer,Integer>>();
		for(int i=0; i<4;i++){
			dgVLocs.add(new Pair<Integer,Integer>(-1,-1));
		}
	}

	@Override
	public void run() {
		while(!disabled){
			for(int i=0;i<this.getChildCount();i++){
				
			}
		}
	}
	
	public void positionGroupViewTabs(){
		//got a bug here somewhere. To do with the place holders I think
		for(int i=0;i<dgViews.size();i++){
			DiceGroupView dgv=dgViews.get(i);
			Pair<Integer,Integer> dgl=dgVLocs.get(i);
			DiceView dv = dgv.getTopRight();
			if (dgv.hasDice() && dgl.first==-1){
				TableRow r=(TableRow)this.getChildAt(dv.getRow()*2);
				r.removeViewAt(dv.getColumn());
				insertDGV(dgv, dv);
				updateLocation(i,dv);
			}
			else if(!dgv.hasDice() && dgl.first!=-1){
				removeDGV(i, dgv);
				updateLocation(i,dv);
			}			
			else if(dv!=null){
				if (dv.getRow()!=dgl.first || dv.getColumn()!=dgl.second){
					removeDGV(i, dgv);
					insertDGV(dgv, dv);
					updateLocation(i,dv);
				}
			}
		}
	}

	private void removeDGV(int locIndex, DiceGroupView dgv) {
		TableRow r=(TableRow)this.getChildAt(dgVLocs.get(locIndex).first*2);
		r.removeViewAt(dgVLocs.get(locIndex).second);
		r.addView(new ImageView(this.getContext()),dgVLocs.get(locIndex).second);
	}

	private void updateLocation(int locIndex, DiceView topRight) {
		dgVLocs.remove(locIndex);
		if(topRight==null)dgVLocs.add(locIndex, new Pair<Integer,Integer>(-1,-1));
		else dgVLocs.add(locIndex, new Pair<Integer,Integer>(topRight.getRow(),topRight.getColumn()));
	}

	private void insertDGV(DiceGroupView dgv, DiceView topRight) {
		TableRow r=(TableRow)this.getChildAt(topRight.getRow()*2);
		r.addView(dgv, topRight.getColumn());
	}
	
	
	

}
