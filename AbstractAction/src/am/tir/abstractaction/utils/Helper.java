package am.tir.abstractaction.utils;

public class Helper {

	private Helper() {}
	
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
}
