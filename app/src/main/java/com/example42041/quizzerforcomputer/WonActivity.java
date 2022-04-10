package com.example42041.quizzerforcomputer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WonActivity extends Activity {
    TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        this.textView = (TextView) findViewById(R.id.score_txt);
        TextView textView2 = this.textView;
        textView2.setText(getIntent().getStringExtra("mytext") + " + 5");
    }

    public void PlayAgain(View view) {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }
}
