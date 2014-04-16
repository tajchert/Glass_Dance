package com.tajchert.glassware.dance;

public class DanceMove {
	
	public int leftFeetX;
	public int leftFeetY;
	public int rightFeetX;
	public int rightFeetY;
	
	public long time = 0l;

	/**
	 * @param leftFeetX
	 * @param leftFeetY
	 * @param rightFeetX
	 * @param rightFeetY
	 * @param time
	 */
	public DanceMove(int leftFeetX, int leftFeetY, int rightFeetX,
			int rightFeetY, long time) {
		super();
		this.leftFeetX = leftFeetX;
		this.leftFeetY = leftFeetY;
		this.rightFeetX = rightFeetX;
		this.rightFeetY = rightFeetY;
		this.time = time;
	}

}
