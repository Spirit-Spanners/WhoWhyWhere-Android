package am.tir.abstractaction.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Helper {

	private Helper() {}
	
	private static Typeface hobo;
	private static Typeface seriously;
	
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	public static Typeface getHoboTypeface(Context context) {
		if (hobo == null) { 
			hobo = Typeface.createFromAsset(context.getAssets(), "fonts/Hobo.ttf");
		}
		return hobo;
	}
	
	public static Typeface getSeriouslyTypeface(Context context) {
		if (seriously == null) {
			seriously = Typeface.createFromAsset(context.getAssets(), "fonts/seriously.ttf");
		}
		return seriously;
	}
}
