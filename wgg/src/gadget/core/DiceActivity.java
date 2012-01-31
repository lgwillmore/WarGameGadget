package gadget.core;

import java.util.HashMap;

import game.core.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DiceActivity extends Activity {
	
	private DiceManager dMan;
	private DiceTableView dView;
	private DisplayMetrics screen;
	
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dMan=new DiceManager();
        screen=new DisplayMetrics();       
        getWindowManager().getDefaultDisplay().getMetrics(screen);
        DiceTableFactory df = new DiceTableFactory(this,screen,dMan);
        dView=df.getDiceTableView();
        setContentView(dView);
        new Thread(dView).start();
    }
    
    
}