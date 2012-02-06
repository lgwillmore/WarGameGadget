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

public class DiceTableView2 extends TableLayout{	
	private  double diceWidth=16,drHeight=0, drPadding=0, grHeight=0, grWidth=0,buttonRowHeight=0;
	private Context context;
	
	
	public DiceTableView2(Context context,DisplayMetrics screen) {
		super(context);
		this.context=context;
		calculateScales(screen.widthPixels,screen.heightPixels);
		build();
	}

	private void calculateScales(int width,int height) {		
		diceWidth= ((diceWidth/100)*width);
		drPadding=diceWidth/20;
		drHeight=diceWidth+2*drPadding;
		grHeight=(drHeight/4);
		buttonRowHeight=(drHeight+grHeight);
	}
	
	

	public void build(){
		for(int i=0;i<6;i++){
			TableRow g=new TableRow(context);
			g.setBackgroundColor(Color.BLUE);
			TableRow r=new TableRow(context);
			r.setBackgroundColor(Color.GRAY);
			for (int j=0;j<5;j++){
				ImageView iv=new ImageView(context);
				iv.setImageResource(R.drawable.w_one);
				iv.setAdjustViewBounds(true);
				iv.setMaxHeight((int)diceWidth);
				iv.setMaxWidth((int)diceWidth);
				iv.setPadding((int)drPadding, 0, (int)drPadding, 0);
				r.addView(iv);
			}
			g.setMinimumHeight((int)grHeight);
			r.setMinimumHeight((int)drHeight);
			r.setPadding((int)drPadding, (int)drPadding, (int)drPadding, (int)drPadding);
			this.addView(g);
			this.addView(r);
		}
	}
	
	
	

}
