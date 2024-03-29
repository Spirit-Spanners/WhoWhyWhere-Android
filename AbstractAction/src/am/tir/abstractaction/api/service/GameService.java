package am.tir.abstractaction.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import am.tir.abstractaction.api.parser.AnswerParser;
import am.tir.abstractaction.api.parser.GetAnswersParser;
import am.tir.abstractaction.api.parser.GetRandomAnswerParser;
import am.tir.abstractaction.api.parser.GetStoriesParser;
import am.tir.abstractaction.api.parser.StartGameParser;
import am.tir.abstractaction.net.SimpleHttpClient;
import static am.tir.abstractaction.utils.Properties.*;
import android.content.Context;
import android.os.Handler;

public class GameService {

	private GameService() {}
	
	public static void startGame(int requestId, Context context, Handler handler) {
		String urlString = APP_URL + URL_SUFFIX_START_GAME;
		StartGameParser startGameParser = new StartGameParser(requestId, handler);
		
		SimpleHttpClient httpClient = new SimpleHttpClient(urlString, startGameParser, context);
		httpClient.execute();
	}
	
	public static void answer(long gameId, int questionId, String answer, int requestId, Context context, Handler handler) {
		StringBuilder urlBuilder = new StringBuilder(APP_URL);
		urlBuilder.append(URL_SUFFIX_ANSWER);
		urlBuilder.append(URL_PARAM_GAME_ID).append(gameId);
		urlBuilder.append(URL_PARAM_SEPARATOR).append(URL_PARAM_QUESTION_ID).append(questionId);
		try {
			urlBuilder.append(URL_PARAM_SEPARATOR).append(URL_PARAM_ANSWER).append(URLEncoder.encode(answer, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		AnswerParser answerParser = new AnswerParser(requestId, handler);
		SimpleHttpClient simpleHttpClient = new SimpleHttpClient(urlBuilder.toString(), answerParser, context);
		simpleHttpClient.execute();
	}
	
	public static void getAnswerList(int questionId, int requestId, Context context, Handler handler) {
		StringBuilder urlStringBuilder = new StringBuilder(APP_URL);
		urlStringBuilder.append(URL_SUFFIX_GET_ANSWER_LIST);
		urlStringBuilder.append(URL_PARAM_QUESTION_ID).append(questionId);
		
		GetAnswersParser parser = new GetAnswersParser(requestId, handler);
		SimpleHttpClient simpleHttpClient = new SimpleHttpClient(urlStringBuilder.toString(), parser, context);
		simpleHttpClient.execute();
	}
	
	public static void getRandomAnswer(int questionId, int requestId, Context context, Handler handler) {
		StringBuilder urlStringBuilder = new StringBuilder(APP_URL);
		urlStringBuilder.append(URL_SUFFIX_GET_RANDOM_ANSWER);
		urlStringBuilder.append(URL_PARAM_QUESTION_ID).append(questionId);
		
		GetRandomAnswerParser randomAnswerParser = new GetRandomAnswerParser(requestId, handler);
		SimpleHttpClient simpleHttpClient = new SimpleHttpClient(urlStringBuilder.toString(), randomAnswerParser, context);
		simpleHttpClient.execute();
	}
	
	public static void getStoryList(int gameId, int requestId, Context context, Handler handler) {
		StringBuilder urlBuilder = new StringBuilder(APP_URL);
		urlBuilder.append(URL_SUFFIX_GET_STORY_LIST);
		urlBuilder.append(URL_PARAM_GAME_ID).append(gameId);
		
		GetStoriesParser storiesParser = new GetStoriesParser(requestId, handler);
		SimpleHttpClient httpClient = new SimpleHttpClient(urlBuilder.toString(), storiesParser, context);
		httpClient.execute();
	}
}
