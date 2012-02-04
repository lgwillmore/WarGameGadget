package gadget.core;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;

public class DiceGroupView {
	private ArrayList<DiceView> diceVs;
	private DiceGroup dGroup;
	private ArrayList<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> lines;
	DiceView TL,LT,BR,RB=null;//corner DiceViews
	private static final int WHITE=0,RED=1,BLUE=2,GREEN=3;
	private int pu,topBorder,leftBorder, rbBorder,tagwidth,tagheight;
	
	public DiceGroupView(DiceGroup dg,int pu,int tb,int lb,int rbb,
			int tagwidth,int tagheight){
		diceVs=new ArrayList<DiceView>();
		dGroup=dg;
		this.topBorder=tb;
		leftBorder=lb;
		rbBorder=rbb;
		this.pu=pu;
		this.tagwidth=tagwidth;
		this.tagheight=tagheight;
	}
	
	public void addDiceV(DiceView dv){
		diceVs.add(dv);
	}

	public void draw(Canvas c) {
		for(DiceView dv:diceVs)dv.draw(c);
		if(diceVs.size()>0){
			getCorners();
			Paint p=getPaint();
			//draw square
			if(TL==LT&&BR==RB){
				c.drawRect(getLeftLine(LT,BR),p);
				c.drawRect(getRightLine(TL,RB),p);
				c.drawRect(getTopTopLine(TL,RB),p);
				c.drawRect(getBotBotLine(LT,BR), p);
				c.drawRect(getTag(TL,RB), p);
			}
			//draw br corner missing
			else if(TL==LT&&BR!=RB){
				
			}
			//draw tl corner missing
			else if(TL!=LT&&BR==RB){
				
			}
			//draw tl and br corner missing
			else{
				
			}
		}
	}
	
	private Rect getTag(DiceView tl, DiceView rb) {
		int l=rb.getX()+rbBorder-tagwidth;
		int t=tl.getY()-topBorder;
		int r=rb.getX()+rbBorder;
		int b=tl.getY()-topBorder+tagheight;
		return new Rect(l,t,r,b);
	}

	private Paint getPaint() {
		switch(dGroup.getColour()){
		case WHITE:
			Paint p=new Paint();
			p.setARGB(150, 255, 255, 255);
			return p;
		case RED:return new Paint(Color.RED);
		case BLUE :return new Paint(Color.BLUE);
		case GREEN:return new Paint(Color.GREEN);
		default:return null;
		}
	}

	private Rect getBotBotLine(DiceView left, DiceView right) {
		int l=left.getX()-leftBorder;
		int t=right.getY()+rbBorder-pu;
		int r=right.getX()+rbBorder;
		int b=right.getY()+rbBorder;
		return new Rect(l,t,r,b);
	}

	private Rect getTopTopLine(DiceView left, DiceView right) {
		int l=left.getX()-leftBorder;
		int t=left.getY()-topBorder;
		int r=right.getX()+rbBorder;
		int b=left.getY()-topBorder-pu;
		return new Rect(l,t,r,b);
	}

	private Rect getRightLine(DiceView top, DiceView bot) {
		int l=bot.getX()+rbBorder-pu;
		int t=top.getY()-topBorder+tagheight;
		int r=bot.getX()+rbBorder;
		int b=bot.getY()+rbBorder-pu;
		return new Rect(l,t,r,b);
	}

	public Rect getLeftLine(DiceView top, DiceView bot){
		int l=top.getX()-leftBorder;
		int t=top.getY()-topBorder;
		int r=top.getX()-leftBorder+pu;
		int b=bot.getY()+rbBorder-pu;
		return new Rect(l,t,r,b);
	}
	
	/*
	 * Updates the corner most DiceViews
	 */
	private void getCorners() {
		int bot=0;
		int top=10000;
		int left=10000;
		int right=0;
		ArrayList<DiceView> brow=new ArrayList<DiceView>();
		ArrayList<DiceView> trow=new ArrayList<DiceView>();
		ArrayList<DiceView> rcol=new ArrayList<DiceView>();
		ArrayList<DiceView> lcol=new ArrayList<DiceView>();
		for(DiceView dv:diceVs){
			//populate the bottom row
			if(dv.getY()>bot){
				brow.clear();
				brow.add(dv);
				bot=dv.getY();
			}
			else if(dv.getY()==bot)brow.add(dv);
			//populate the top row
			if(dv.getY()<top){
				trow.clear();
				trow.add(dv);
				top=dv.getY();
			}
			else if(dv.getY()==top)trow.add(dv);
			//populate the left column
			if(dv.getX()<left){
				lcol.clear();
				lcol.add(dv);
				left=dv.getX();
			}
			else if(dv.getX()==left)lcol.add(dv);
			//populate the right column
			if(dv.getX()>right){
				rcol.clear();
				rcol.add(dv);
				right=dv.getX();
			}
			else if(dv.getX()==right)rcol.add(dv);
		}
		//get the rightmost in the bottom
		right=0;
		for(DiceView dv:brow)
			if(dv.getX()>right){
			BR=dv;
			right=dv.getX();}
		//get the leftmost in the top
		left=10000;
		for(DiceView dv:trow)
			if(dv.getX()<left){
				TL=dv;
				left=dv.getX();
			}
		//get the topmost in the left
		top=10000;
		for(DiceView dv:lcol)
			if(dv.getY()<top){
				LT=dv;
				top=dv.getY();
			}
		//get the bottom-most in the right
		bot=0;
		for(DiceView dv:rcol)
			if(dv.getY()>bot){
				RB=dv;
				bot=dv.getY();
			}
	}
}
