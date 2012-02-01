package gadget.core;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class DiceView {
	private float x,y,width;
	private Dice dice;
	
	public DiceView(int x, int y, int width, Dice d){
		this.x=new Float(x);
		this.y=new Float(y);
		this.width=new Float(width);
		this.dice=d;
		
	}
	public void draw(Canvas c) {
		Paint p=new Paint();
		p.setColor(Color.BLUE);
		int cwidth=c.getWidth();
		c.drawRect(x, y, x+width, y+width, p);
	}
}
