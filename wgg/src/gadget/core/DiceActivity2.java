package gadget.core;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiceActivity2 extends Activity {
	
	private DisplayMetrics screen;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		screen = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(screen);
		
		DiceTableView2 dt =new DiceTableView2(this,screen);
		setContentView(dt);
		
	}

	
}