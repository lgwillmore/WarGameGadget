package gadget.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DiceTableView extends TableLayout{
	
	
	
	public DiceTableView(Context context, int heightPixels, int widthPixels ){
		super(context);
		int dlength=widthPixels/5;
		for(int i= 0; i<6;i++){
			TableRow r =new TableRow(this.getContext());
			for(int j =0;j<5;j++){				
				r.addView(new DiceView(this.getContext(), dlength));
			}
			this.addView(r);
		}
	}
	
	public DiceTableView(Context context) {
		super(context);
	}
	
	
	
	public DiceTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}



	
		

		
	
	
	

}
