package gadget.core;

import android.content.Context;
import android.widget.ImageView;

public class DiceView extends ImageView{
	
	private DiceGroupView parent;
	private Dice dice;

	public DiceView(Context context,Dice d) {
		super(context);
		this.dice=d;
	}

	public void setParent(DiceGroupView dgv) {
		parent=dgv;
	}

}
