package com.example42041.quizzerforcomputer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {
    Button playAgain;
    TextView wrongAnsText;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);
        this.playAgain = (Button) findViewById(R.id.playAgainButton);
        this.wrongAnsText = (TextView) findViewById(R.id.wrongAns);
        this.playAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GameOverActivity.this.startActivity(new Intent(GameOverActivity.this, GameActivity.class));
                GameOverActivity.this.finish();
            }
        });
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main-Bold.otf");
        this.playAgain.setTypeface(typeface);
        this.wrongAnsText.setTypeface(typeface);
    }

    public void onBackPressed() {
        finish();
    }
}
