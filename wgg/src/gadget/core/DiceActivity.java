package gadget.core;

import java.util.ArrayList;
import game.core.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiceActivity extends Activity implements OnClickListener,OnTouchListener  {

	private DiceManager dMan;
	private DiceTableView dView;
	private DisplayMetrics screen;
	private LinearLayout top;
	Button roll;
	TextView out;
	GestureDetector gd;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gd = new GestureDetector(new MyGestureDetector());
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
		
		/*
		out=new TextView(this);
		out.setText("Ready to Test stuff");
		top.addView(out);*/
				
				
				
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
		return gd.onTouchEvent(me);
		/*
		v.post(new DTouchRun(v,me));
		return false;*/
	}
	
	private class DTouchRun implements Runnable{
		private MotionEvent me;
		public DTouchRun(MotionEvent me){
			this.me=me;
		}

		@Override
		public void run() {
			int[] coords=new int[]{1,1};//holder for dviews location on screen
			top.getLocationOnScreen(coords);//get dviews location
			//use adjusted raw coords of event to get the relative view coords.
			Interactible i=dView.getInteractible(me.getRawX()-coords[0],me.getRawY()-coords[1]);
			if(i!=null)i.touched();
		}
		
	}
	
	/*
	 * A Listener to handle a bunch of the mechanics of the screen being touched.
	 */
	private class MyGestureDetector extends SimpleOnGestureListener{

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onDown(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent me) {
			dView.post(new DTouchRun(me));
			return true;
		}

		
	}
	

	
}