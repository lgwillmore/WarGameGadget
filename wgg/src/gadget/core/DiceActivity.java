package gadget.core;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiceActivity extends Activity {
	
	private DisplayMetrics screen;
	private DiceManager dm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		screen = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(screen);
		dm=new DiceManager();
		DiceTableView dt =new DiceTableFactory(this,screen,dm).getDiceTableView();
		setContentView(dt);
		
	}

	
}