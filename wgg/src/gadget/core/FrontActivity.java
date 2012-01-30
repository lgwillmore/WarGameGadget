package gadget.core;

import game.core.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class FrontActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        setContentView(new DiceTableView(this,displaymetrics.heightPixels,displaymetrics.widthPixels));       
    }
    
    
}