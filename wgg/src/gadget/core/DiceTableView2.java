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

public class DiceTableView2 extends TableLayout implements Runnable{	
	private  double diceWidth=0.16,dicePadding=0.02,grButtonHeight=0.06,grRowPadding=0.01;
	private int dw,dp,gbh,grp;
	private Context context;
	private boolean disabled=false;
	
	
	public DiceTableView2(Context context,DisplayMetrics screen) {
		super(context);
		this.context=context;
		calculateScales(screen.widthPixels,screen.heightPixels);
		build();
	}

	private void calculateScales(int width,int height) {		
		dw= (int)(diceWidth*width);
		dp=(int)(dicePadding*width);
		gbh=(int)(grButtonHeight*width);
		grp=(int)(grRowPadding*width);
		double r=((width-((5*dw)+(10*dp)))/5);
		dw=dw+(int)r;
	}
	
	

	public void build(){
		for(int i=0;i<6;i++){
			TableRow g=new TableRow(context);
			//g.setBackgroundColor(Color.BLUE);
			TableRow r=new TableRow(context);
			//r.setBackgroundColor(Color.GRAY);
			for (int j=0;j<5;j++){
				ImageView iv=new ImageView(context);
				iv.setImageResource(R.drawable.w_one);
				iv.setAdjustViewBounds(true);
				iv.setMaxHeight(dw+2*dp);
				iv.setMaxWidth(dw+2*dp);
				//iv.setBackgroundColor(Color.GREEN);
				iv.setPadding(dp, 0, dp, 0);
				r.addView(iv);
			}
			g.setMinimumHeight(gbh);
			g.setPadding(grp, grp, grp, grp);
			r.setMinimumHeight(dw);
			//r.setPadding((int)drPadding, (int)drPadding, (int)drPadding, (int)drPadding);
			this.addView(g);
			this.addView(r);
			DiceGroupView2 dgv=new DiceGroupView2(context);
			dgv.setText("4+");
			dgv.setBackgroundColor(Color.BLUE);
			dgv.setMaxHeight(gbh);
			dgv.setPadding(dw/2, 0, 0, 0);
			g.addView(dgv);
		}
	}

	@Override
	public void run() {
		while(!disabled){
			for(int i=0;i<this.getChildCount();i++){
				
			}
		}
	}
	
	
	

}
