package am.tir.abstractaction.activity;

import java.util.List;

import am.tir.abstractaction.R;
import am.tir.abstractaction.api.parser.ResponseParser;
import am.tir.abstractaction.api.service.GameService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.app.Activity;
import android.app.ProgressDialog;

public class Game extends Activity implements Callback {

	private static final int ID_REQUEST_START_GAME = 1;
	private static final int ID_REQUEST_GET_ANSWERS_LIST = 2;

	private Handler handler = new Handler(this);

	private ProgressDialog progressDialog;

	private int gameId;
	private int currentQuestionId;

	private List<String> answers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		showProgressDialog();
		GameService.startGame(ID_REQUEST_START_GAME, this, handler);
	}

	public void onSubmitClick(View view) {
		showProgressDialog();

	}

	public void onSelectAnswerClick(View view) {
		if (answers == null) {
			showProgressDialog();
			GameService.getAnswerList(ID_REQUEST_GET_ANSWERS_LIST,
					currentQuestionId, this, handler);
		}
	}

	public void onSelectRandomAnswrClick(View view) {

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
			progressDialog.cancel();
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		hideProgressDialog();
		boolean isStatusOk = checkStatus(msg.what);
		if (isStatusOk) {
			Bundle data = msg.getData();

		}
		return true;
	}

	private void handleStartGameMSG(Bundle data) {
		gameId = data.getInt(ResponseParser.RESULT);
	}

	private boolean checkStatus(int status) {
		// TODO implemant
		return true;
	}
}
