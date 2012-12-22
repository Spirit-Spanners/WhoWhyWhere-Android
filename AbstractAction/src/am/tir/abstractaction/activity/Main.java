package am.tir.abstractaction.activity;

import am.tir.abstractaction.R;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    } 
    
    public void onEnterClick(View view) {
    	Intent intent = new Intent(this, Game.class);
    	startActivity(intent);
    }
}
