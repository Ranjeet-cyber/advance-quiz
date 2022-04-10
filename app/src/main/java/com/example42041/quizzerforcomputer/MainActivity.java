package com.example42041.quizzerforcomputer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button playGame;
    Button quit;
    Button share;
    TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home_screen);
        setTitle("Advance Quiz App");
        initViews();
        this.playGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, GameActivity.class));
                MainActivity.this.finish();
            }
        });
        this.share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent sharingIntent = new Intent("android.intent.action.SEND");
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra("android.intent.extra.TEXT", "Try this new ** App   ");
                sharingIntent.putExtra("android.intent.extra.SUBJECT", "Subject");
                MainActivity.this.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
        this.quit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog show = new AlertDialog.Builder(MainActivity.this).setIcon((int) R.drawable.alrt).setTitle((CharSequence) "Are you sure to Exit").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    @SuppressLint("WrongConstant")
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "Welcome Back", 1).show();
                    }
                }).show();
            }
        });
    }

    private void initViews() {
        this.playGame = (Button) findViewById(R.id.playGame);
        this.quit = (Button) findViewById(R.id.exit);
        this.textView = (TextView) findViewById(R.id.tQ);
        this.share = (Button) findViewById(R.id.share);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/main-Bold.otf");
        this.playGame.setTypeface(typeface);
        this.quit.setTypeface(typeface);
        this.textView.setTypeface(typeface);
    }
}
