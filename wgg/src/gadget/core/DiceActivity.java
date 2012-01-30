package gadget.core;

import game.core.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DiceActivity extends Activity {
	
	private DiceTableView table;
	private Display screen;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen=getWindowManager().getDefaultDisplay();
        table=new DiceTableView(this, screen);
        
        setContentView(table);       
    }
    
    public class DiceTableView extends SurfaceView{
    	
    	int dice;
    	int border;
    	int gap;
    	int topgap;
    	int tagwidth,taglength;
    	private SurfaceHolder holder;
    	Display screen;

    	public DiceTableView(Context context, Display screen) {
    		super(context);
    		calculateDimensions(screen);
    		holder=this.getHolder();
    	}
    	
    	public void calculateDimensions(Display screen){
    		
    	}
    }
    
    
}