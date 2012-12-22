package am.tir.abstractaction.api.service;

import am.tir.abstractaction.api.parser.StartGameParser;
import am.tir.abstractaction.utils.Properties;
import android.content.Context;
import android.os.Handler;

public class GameService {

	private GameService() {}
	
	public static void startGame(int requestId, Context context, Handler handler) {
		String urlString = Properties.APP_URL + "startgame.php";
		StartGameParser startGameParser = new StartGameParser(requestId, handler);
		
	}
	
	public static void answer(long gameId, int questionId, String answer, int requestId, Context context, Handler handler) {
		
	}
	
	public static void getAnswerList(int questionId, int requestId, Context context, Handler handler) {
		
	}
	
	public static void getRandomAnswer(int questionId, int requestId, Context context, Handler handler) {
		
	}
}
