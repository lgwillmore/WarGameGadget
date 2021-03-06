package gadget.core;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DiceView implements Interactible{
	private int x,y,width;
	int pu;
	private Dice dice;
	private static ArrayList<ArrayList<Bitmap>> diceFaces;
	private static final int WHITE=0,RED=1,BlUE=2,GREEN=3;
	
	
	
	public DiceView(int x, int y, int width,int pu, Dice d){
		this.x=x;
		this.y=y;
		this.width=width;
		this.dice=d;
		this.pu=pu;
	}
	public void draw(Canvas c) {
		Paint p=new Paint();
		p.setColor(Color.BLUE);
		if(dice.isSelected())c.drawRect(x-pu, y-pu, x+width+pu, y+width+pu, p);
		c.drawBitmap(lookUpDiceFace(), x, y, null);
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
	public void singleTapped() {
		dice.setSelected();
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getPu() {
		return pu;
	}
	public int getWidth() {
		return width;
	}
	@Override
	public void longPressed() {
		// TODO Auto-generated method stub
		
	}
	
	public String getMessage(){
		return "I am here";
	}
	
	
}
