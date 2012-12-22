package am.tir.abstractaction.utils;

import android.graphics.Typeface;

public class Helper {

	private Helper() {}
	
	private static Typeface hobo;
	
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	public static Typeface getHoboTypeface() {
		if (hobo == null) {
			
		}
		return hobo;
	}
}
