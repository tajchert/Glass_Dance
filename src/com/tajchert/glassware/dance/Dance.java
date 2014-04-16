package com.tajchert.glassware.dance;

import java.util.ArrayList;


public class Dance {
	public String title;
	public int difficulty;
	public ArrayList<DanceMove> moves = new ArrayList<DanceMove>();
	
	/**
	 * @param title
	 * @param difficulty
	 * @param moves
	 */
	public Dance(String title, int difficulty) {
		super();
		this.title = title;
		this.difficulty = difficulty;
	}
}
