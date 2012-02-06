package gadget.core;

import game.core.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.widget.ImageView;
import android.widget.TableRow;

public class DiceTableFactory {

	
	private DisplayMetrics screen;
	private DiceManager dm;
	private Context context;
	private ArrayList<DiceGroupView> dgvs;
	private DiceTableView dtv;
	private  double diceWidth=0.16,dicePadding=0.02,grButtonHeight=0.06,grRowPadding=0.01;
	private int dw,dp,gbh,grp;
	

	public DiceTableFactory(Context c,DisplayMetrics screen, DiceManager dm) {
		this.screen=screen;
		this.dm=dm;
		context=c;	}
	
	public DiceTableView getDiceTableView(){
		createDiceGroups();
		dtv=new DiceTableView(context,dgvs);
		calculateSpacing(screen.widthPixels,screen.heightPixels);
		addDiceViews();
		return dtv;
	}	

	private void createDiceGroups() {
		dgvs=new ArrayList<DiceGroupView>();
		for(int i=0;i<4;i++){
			dgvs.add(new DiceGroupView(context,dm.getDiceGroup(i)));
		}
	}

	private void addDiceViews() {
		for(int i=0;i<6;i++){
			TableRow g=new TableRow(context);
			TableRow r=new TableRow(context);
			
			for (int j=0;j<5;j++){
				Dice d=new Dice();
				DiceView dv=new DiceView(context,d);
				//add dice to dicemanager and group.
				dm.addDice(d);
				//add diceview to a group
				dgvs.get(Util.WHITE).addDiceView(dv);
				dv.setImageResource(R.drawable.w_one);
				dv.setAdjustViewBounds(true);
				dv.setMaxHeight(dw+2*dp);
				dv.setMaxWidth(dw+2*dp);
				//iv.setBackgroundColor(Color.GREEN);
				dv.setPadding(dp, 0, dp, 0);
				r.addView(dv);
			}
			g.setMinimumHeight(gbh);
			g.setPadding(grp, grp, grp, grp);
			r.setMinimumHeight(dw);
			dtv.addView(g);
			dtv.addView(r);
		}
	}

	public void calculateSpacing(int width,int height){
		dw= (int)(diceWidth*width);
		dp=(int)(dicePadding*width);
		gbh=(int)(grButtonHeight*width);
		grp=(int)(grRowPadding*width);
		double r=((width-((5*dw)+(10*dp)))/5);
		dw=dw+(int)r;
	}
	
}


