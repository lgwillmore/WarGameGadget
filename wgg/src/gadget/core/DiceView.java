package gadget.core;

import android.R;
import android.content.Context;
import android.widget.ImageView;

public class DiceView {
	private int x,y,width;
	private Dice dice;
	public DiceView(int x, int y, int width, Dice d){
		this.x=x;
		this.y=y;
		this.width=width;
		this.dice=d;
	}
}
