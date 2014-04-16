package com.tajchert.glassware.dance;

public class InitDance {
			//Center left - 140,210
			//left bottom left - 20,390
			//left bottom right - 220, 390
			//left top right - 220,20
			//left top left - 20,20
			
			
			//Center left - 210,210
			//right bottom left - 90,390
			//right bottom right - 290,390
			//right top right - 290, 20
			//right top left - 90, 20
	
	public static Dance initEnglishWaltz(){
		Dance waltz = new Dance("English Waltz", 1);
		
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 0));
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 1000));
		waltz.moves.add(new DanceMove(20, 390, 90, 20, 2000));
		waltz.moves.add(new DanceMove(20, 390, 290, 390, 3000));
		waltz.moves.add(new DanceMove(220, 390, 290, 390, 4000));
		waltz.moves.add(new DanceMove(220, 390, 290, 20, 5000));
		waltz.moves.add(new DanceMove(20, 20, 290, 20, 6000));
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 7000));
		
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 8000));
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 8100));
		return waltz;
	}
	public static Dance initViennaWaltz(){
		Dance waltz = new Dance("Vienna Waltz", 1);
		
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 0));
		waltz.moves.add(new DanceMove(20, 20, 90, 20, 1000));
		waltz.moves.add(new DanceMove(20, 390, 90, 20, 2000));
		waltz.moves.add(new DanceMove(20, 390, 290, 390, 3000));
		waltz.moves.add(new DanceMove(220, 390, 290, 390, 4000));
		waltz.moves.add(new DanceMove(220, 390, 290, 390, 4000));
		return waltz;
	}

}
