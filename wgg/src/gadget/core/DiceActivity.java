package gadget.core;

import java.util.ArrayList;
import game.core.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class DiceActivity extends Activity implements OnClickListener,OnTouchListener  {

	private DiceManager dMan;
	private DiceTableView dView;
	private DisplayMetrics screen;
	private LinearLayout top;
	Button roll;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dMan = new DiceManager();
		screen = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(screen);
		DiceTableFactory df = new DiceTableFactory(this, screen, dMan,
				buildBitmaps());
		dView = df.getDiceTableView();
		buildLayout();
		setContentView(top);
		new Thread(dView).start();
	}

	private void buildLayout() {
		top = new LinearLayout(this);
		top.setOrientation(LinearLayout.VERTICAL);
		dView.setId(1);
		dView.setOnTouchListener(this);
		top.addView(dView);
		roll = new Button(this);
		roll.setOnClickListener(this);
		roll.setText(R.string.rollAllButton);
		roll.setId(0);
		top.addView(roll);
	}

	private ArrayList<ArrayList<Bitmap>> buildBitmaps() {
		//
		ArrayList<ArrayList<Bitmap>> diceFaces = new ArrayList<ArrayList<Bitmap>>();
		for (int i = 0; i < 4; i++) {
			diceFaces.add(new ArrayList<Bitmap>());
		}
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_one));
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_two));
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_three));
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_four));
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_five));
		diceFaces.get(0).add(
				BitmapFactory.decodeResource(this.getResources(), R.drawable.w_six));
		return diceFaces;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 0:
			v.post(new Runnable() {
				@Override
				public void run() {
					dMan.rollAll();
				}
			});break;
		default:break;
		}
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent me) {
		v.post(new DTouchRun(v,me));
		return false;
	}
	
	private class DTouchRun implements Runnable{
		private View v;
		private MotionEvent me;
		public DTouchRun(View v, MotionEvent me){
			this.v=v;
			this.me=me;
		}

		@Override
		public void run() {
			int[] coords=new int[]{1,1};//holder for dviews location on screen
			v.getLocationOnScreen(coords);//get dviews location
			//use adjusted raw coords of event to get the relative view coords.
			Interactible i=dView.getInteractible(me.getRawX()-coords[0],me.getRawY()-coords[1]);
			if(i!=null)i.touched();
		}
		
	}
	
	/*
	 * OnTouch listener is the listener I want to use. I just need to query the mes to work out what sort of gesture is occuring.
	 */

	

	
}