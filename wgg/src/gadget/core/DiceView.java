package gadget.core;

import android.content.Context;
import android.widget.ImageView;

public class DiceView extends ImageView{
	
	private DiceGroupView parent;
	private Dice dice;
	private int row,column;

	public DiceView(Context context,Dice d,int row,int col) {
		super(context);
		this.dice=d;
		this.row=row;
		this.column=col;
	}

	public void setParent(DiceGroupView dgv) {
		parent=dgv;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	
}
