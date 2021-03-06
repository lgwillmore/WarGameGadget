package gadget.core;

import java.util.Random;


public class Dice implements Runnable{
	private Random myRand;
	private int SIDES;
	private int SIDE_UP;
	private static final long MAX_TOTAL_ROLLTIME = 6000;// milliseconds
	private static final long MAX_AIRTIME = 500;// milliseconds
	private static final double BOUNCE_PROBABILITY = 0.80;
	private long startTime;
	private int groupColour;
	private boolean selected=false;
	
	
	public Dice(int colour){
		SIDES=6;
		groupColour=colour;
		init();
	}
	
	
	
	private void init(){
		myRand=new Random();
		long seed= (long)(Math.random()*10000);
		myRand.setSeed(System.currentTimeMillis()+seed);
		SIDE_UP=1;
	}
	
	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		longRoll();
	}
	
	private void longRoll() {
		
			try {
				Thread.sleep(getAirTime());
			} catch (InterruptedException e) {
			}
			roll();
			if (bounceAgain() && (getTotalTime() < MAX_TOTAL_ROLLTIME))
				longRoll();
		
	}
	
	public void roll(){
		SIDE_UP = 1 + (int)(SIDES*myRand.nextDouble());
	}
	
	private long getAirTime() {
		return (long) (myRand.nextDouble() * MAX_AIRTIME);
	}

	private boolean bounceAgain() {
		double res = myRand.nextDouble();
		if (res <= BOUNCE_PROBABILITY)
			return true;
		return false;
	}

	private long getTotalTime() {
		return System.currentTimeMillis() - startTime;
	}

	public int getSideUp(){
		return SIDE_UP;
	}
	
	public int getGroupColour(){
		return groupColour;
	}
	
	public boolean isSelected(){
		return selected;
	}


	public void setSelected() {
		selected=!selected;
	}
	
	
}