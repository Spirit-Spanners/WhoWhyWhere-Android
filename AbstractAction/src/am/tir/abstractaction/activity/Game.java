package am.tir.abstractaction.activity;

import java.util.ArrayList;
import java.util.List;

import am.tir.abstractaction.R;
import am.tir.abstractaction.api.parser.ResponseParser;
import am.tir.abstractaction.api.service.GameService;
import am.tir.abstractaction.data.beans.Story;
import am.tir.abstractaction.utils.Helper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class Game extends Activity implements Callback, AnimationListener {

	private static final int ID_REQUEST_START_GAME = 1;
	private static final int ID_REQUEST_GET_ANSWERS_LIST = 2;
	private static final int ID_REQUEST_ANSWER = 3;
	private static final int ID_REQUEST_GET_RANDOM_ANSWER = 4;
	private static final int ID_REQUEST_GET_STORY_LIST = 5;

	private Handler handler = new Handler(this);

	private ProgressDialog progressDialog;
	private EditText answerEditText;
	private TextView questionTextView;
	private View paperLayout;

	private int gameId;
	private int currentQuestionId = 1;

	private String[] questions;
	private String[] questionHints;
	private List<String> answers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		questions = getResources().getStringArray(R.array.questions);
		questionHints = getResources().getStringArray(R.array.question_hints);
		
		answerEditText = (EditText) findViewById(R.id.answer);
		questionTextView = (TextView) findViewById(R.id.question);
		paperLayout = findViewById(R.id.paper_layout);
		answerEditText.setHint(questionHints[currentQuestionId - 1]);
		questionTextView.setText(questions[currentQuestionId - 1]);
		
		showProgressDialog();
		GameService.startGame(ID_REQUEST_START_GAME, this, handler);
	}

	public void onSubmitClick(View view) {
		String answer = answerEditText.getText().toString();
		
		if (Helper.isEmpty(answer)) {
			Toast.makeText(this, "Fill in the answer field", Toast.LENGTH_LONG).show();
			return;
		}
		
		showProgressDialog();
		GameService.answer(gameId, currentQuestionId, answer, ID_REQUEST_ANSWER, this, handler);
	
	}

	public void onSelectAnswerClick(View view) {
		if (answers == null) {
			showProgressDialog();
			GameService.getAnswerList(ID_REQUEST_GET_ANSWERS_LIST, currentQuestionId, this, handler);
		}
	}

	public void onSelectRandomAnswrClick(View view) {
		showProgressDialog();
		GameService.getRandomAnswer(currentQuestionId, ID_REQUEST_GET_RANDOM_ANSWER, this, handler);
	}

	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(getString(R.string.progress_dialog_message));
			progressDialog.setCancelable(false);
		}

		if (!progressDialog.isShowing()) {
			progressDialog.show();
		}
	}

	private void hideProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		boolean isStatusOk = checkStatus(msg.what);
		if (isStatusOk) {
			Bundle data = msg.getData();
			switch (msg.arg1) {
			case ID_REQUEST_START_GAME:
				handleStartGameMSG(data);
				break;
			case ID_REQUEST_ANSWER:
				handleAnswerMSG(data);
				break;
			case ID_REQUEST_GET_RANDOM_ANSWER:
				handleGetRandomAnswer(data);
				break;
			case ID_REQUEST_GET_STORY_LIST:
				handleStoriesMSG(data);
				break;
			default:
				break;
			}
		}
		return true;
	}

	private void handleStartGameMSG(Bundle data) {
		hideProgressDialog();
		gameId = data.getInt(ResponseParser.RESULT);
	}
	
	private void handleAnswerMSG(Bundle data) {
		String status = data.getString(ResponseParser.RESULT);
		if (status.equalsIgnoreCase("ok")) {
			if (currentQuestionId == 6) {
				GameService.getStoryList(gameId, ID_REQUEST_GET_STORY_LIST, this, handler);
			} else {
				hideProgressDialog();
				if (currentQuestionId == 5) {
					Button button = (Button) findViewById(R.id.submit_answer);
					button.setText(R.string.button_finish);
				}				
				currentQuestionId++;
				showNext();
			}
		}
	}
	
	private void showNext() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.paper_hide);
		animation.setAnimationListener(this);
		paperLayout.startAnimation(animation);
	}
	
	private void handleGetRandomAnswer(Bundle data) {
		hideProgressDialog();
		String answer = data.getString(ResponseParser.RESULT);
		answerEditText.setText(answer); 
	}
	
	private void handleStoriesMSG(Bundle data) {
		hideProgressDialog();
		
		@SuppressWarnings("unchecked")
		ArrayList<Story> res = (ArrayList<Story>) data.getSerializable(ResponseParser.RESULT);
		
		Intent intent = new Intent(this, ResultStories.class);
		intent.putExtra("stories", res);
		startActivity(intent);
		
		finish();
	}

	private boolean checkStatus(int status) {
		switch (status) {
		case ResponseParser.MSG_OK:
			return true;
		default:
			hideProgressDialog();
			return false;
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		questionTextView.setText(questions[currentQuestionId - 1]);
		answerEditText.setText("");
		answerEditText.setHint(questionHints[currentQuestionId - 1]);
		
		Animation anima = AnimationUtils.loadAnimation(this, R.anim.paper_show);
		paperLayout.startAnimation(anima);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}
}
