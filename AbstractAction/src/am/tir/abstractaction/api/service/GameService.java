package am.tir.abstractaction.api.service;

import am.tir.abstractaction.api.parser.AnswerParser;
import am.tir.abstractaction.api.parser.StartGameParser;
import am.tir.abstractaction.net.SimpleHttpClient;
import am.tir.abstractaction.utils.Properties;
import android.content.Context;
import android.os.Handler;

public class GameService {

	private GameService() {}
	
	public static void startGame(int requestId, Context context, Handler handler) {
		String urlString = Properties.APP_URL + Properties.URL_SUFFIX_START_GAME;
		StartGameParser startGameParser = new StartGameParser(requestId, handler);
		
		SimpleHttpClient httpClient = new SimpleHttpClient(urlString, startGameParser, context);
		httpClient.execute();
	}
	
	public static void answer(long gameId, int questionId, String answer, int requestId, Context context, Handler handler) {
		StringBuilder urlBuilder = new StringBuilder(Properties.APP_URL);
		urlBuilder.append(Properties.URL_SUFFIX_ANSWER);
		urlBuilder.append("?gameid=").append(gameId);
		urlBuilder.append("&questionid=").append(questionId);
		urlBuilder.append("&answer=").append(answer);
		
		AnswerParser answerParser = new AnswerParser(requestId, handler);
		SimpleHttpClient simpleHttpClient = new SimpleHttpClient(urlBuilder.toString(), answerParser, context);
		simpleHttpClient.execute();
	}
	
	public static void getAnswerList(int questionId, int requestId, Context context, Handler handler) {
		
	}
	
	public static void getRandomAnswer(int questionId, int requestId, Context context, Handler handler) {
		
	}
	
	public static void getStoryList(int gameId, int requestId, Context context, Handler handler) {
		
	}
}
