package gadget.core;

import java.util.HashMap;

import game.core.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
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
    	int pu;
    	int[] ratios = {20,1,2,5,20,4};
    	int diceWidth=0,border=1,gap=2,topgap=3,tagwidth=4,taglength=5;
    	int topRowspace,leftColspace,midRowspace,midColSpace;
    	HashMap<Pair,DiceView> diceSlots;
    	private SurfaceHolder holder;
    	Display screen;

    	public DiceTableView(Context context, Display screen) {
    		super(context);
    		calculateSpacing(screen);
    		holder=this.getHolder();
    	}
    	
    	public void calculateSpacing(Display screen){
    		
    		int width=screen.getWidth();
    		int height=screen.getHeight();
    		pu= width/138;
    		for (int i:ratios)i*=pu;
    		topRowspace=ratios[gap]+2*ratios[border]+ratios[topgap];
    		leftColspace=2*ratios[gap]+2*ratios[border];
    		midRowspace=ratios[gap]+4*ratios[border]+ratios[topgap]+ratios[diceWidth];
    		midColSpace=ratios[diceWidth]+4*ratios[border]+2*ratios[gap];
    	}
    	
    	public void buildDice(){
    		diceSlots=new HashMap<Pair, DiceView>();
    		int x=0,y=0,row=0;
    		while(row<6){
    			int col=0;
    			if(row==0)y=y+topRowspace;
    			else y=y+midRowspace;
    			while(col<5){
    				if(col==0)x=x+leftColspace;
    				else x=x+midColSpace;
    				diceSlots.put(new Pair(row,col), new DiceView(x,y,ratios[diceWidth],new Dice()));
    				col++;
    			}
    			row++;
    		}
    	}
    }
    
    
}