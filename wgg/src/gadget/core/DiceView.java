package gadget.core;

import android.R;
import android.content.Context;
import android.widget.ImageView;

public class DiceView extends ImageView{

	public DiceView(Context context, int diceLength) {
		super(context);
		setImageResource(game.core.R.drawable.one);
		this.setAdjustViewBounds(true);
		this.setMaxHeight(diceLength);
		this.setMaxWidth(diceLength);
		this.setPadding(5, 5, 5, 5);
		//margins are done in viewgroup.
	}

}
