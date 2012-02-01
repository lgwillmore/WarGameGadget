package gadget.core;

import java.util.ArrayList;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DiceActivity extends Activity implements OnClickListener{
	
	private DiceManager dMan;
	private DiceTableView dView;
	private DisplayMetrics screen;
	private LinearLayout top;
	
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        
        dMan=new DiceManager();
        screen=new DisplayMetrics();       
        getWindowManager().getDefaultDisplay().getMetrics(screen);
        DiceTableFactory df = new DiceTableFactory(this,screen,dMan,buildBitmaps());
        dView=df.getDiceTableView();
        buildLayout();
        setContentView(top);
        new Thread(dView).start();
    }
    
    private void buildLayout(){
    	top=new LinearLayout(this);
    	top.setOrientation(LinearLayout.VERTICAL);
    	android.view.ViewGroup.LayoutParams p=dView.getLayoutParams();
    	
    	top.addView(dView);
    	Button roll=new Button(this);
    	roll.setOnClickListener(this);
    	roll.setText("Roll");
    	top.addView(roll);
    }
    
    private  ArrayList<ArrayList<Bitmap>> buildBitmaps(){
    	 //
        ArrayList<ArrayList<Bitmap>> diceFaces=new ArrayList<ArrayList<Bitmap>>();
        BitmapFactory bf=new BitmapFactory();
		for(int i=0;i<4;i++){
			diceFaces.add(new ArrayList<Bitmap>());
		}
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_one));
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_two));
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_three));
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_four));
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_five));
		diceFaces.get(0).add(bf.decodeResource(this.getResources(),R.drawable.w_six));
		return diceFaces;
    }

	@Override
	public void onClick(View v) {
		v.post(new Runnable(){

			@Override
			public void run() {
				dMan.rollAll();
				
			}
			
		});
		
	}
    
    
}