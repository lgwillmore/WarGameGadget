package gadget.core;

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class DiceView implements Interactible{
	private float x,y,width;
	private Dice dice;
	private static ArrayList<ArrayList<Bitmap>> diceFaces;
	private static final int WHITE=0,RED=1,BlUE=2,GREEN=3;
	
	
	
	public DiceView(int x, int y, int width, Dice d){
		this.x=new Float(x);
		this.y=new Float(y);
		this.width=new Float(width);
		this.dice=d;
		
	}
	public void draw(Canvas c) {
		/*Paint p=new Paint();
		p.setColor(Color.BLUE);
		c.drawRect(x, y, x+width, y+width, p);*/
		if(!dice.isSelected())c.drawBitmap(lookUpDiceFace(), x, y, null);
		else{
			Paint p=new Paint();
			p.setColor(Color.BLUE);
			c.drawRect(x, y, x+width, y+width, p);
		}
	}
	
	public Bitmap lookUpDiceFace(){
		int num=dice.getSideUp()-1;
		int colour=dice.getGroupColour();
		return diceFaces.get(colour).get(num);
	}
	
	public static void setDiceFaces(ArrayList<ArrayList<Bitmap>> faces){
		diceFaces=faces;
	}
	@Override
	public boolean isMe(float x, float y) {
		if(x>=this.x&&
				x<=(this.x+width)&&
						y>=this.y&&
						y<=(this.y+width))return true;
		return false;
	}
	@Override
	public void touched() {
		dice.setSelected();
	}
}
