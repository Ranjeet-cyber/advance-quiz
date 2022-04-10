package com.example42041.quizzerforcomputer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TimeOverActivity extends AppCompatActivity {
    Button playAgainButton;
    TextView timeUpText;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_time__up);
        this.playAgainButton = (Button) findViewById(R.id.playAgainButton);
        this.timeUpText = (TextView) findViewById(R.id.timeUpText);
        this.playAgainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TimeOverActivity.this.startActivity(new Intent(TimeOverActivity.this, GameActivity.class));
                TimeOverActivity.this.finish();
            }
        });
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main-Bold.otf");
        this.timeUpText.setTypeface(typeface);
        this.playAgainButton.setTypeface(typeface);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
