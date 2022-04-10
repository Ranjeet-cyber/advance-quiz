package com.example42041.quizzerforcomputer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    QuizHelper JAVAQuizHelper;
    TextView QuizText;
    RadioButton buttonA;
    RadioButton buttonB;
    RadioButton buttonC;
    RadioButton buttonD;
    TextView coinText;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    QuestionActivity currentQuestion;
    Typeface fontone;
    Typeface fonttwo;
    List<QuestionActivity> list;
    MediaPlayer playercorrect;
    MediaPlayer playerwrong;
    int qid = 0;
    TextView questionText;
    TextView resultText;
    TextView timeText;
    int timeValue = 20;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_game_main);
        this.questionText = (TextView) findViewById(R.id.Question);
        this.buttonA = (RadioButton) findViewById(R.id.buttonA);
        this.buttonB = (RadioButton) findViewById(R.id.buttonB);
        this.buttonC = (RadioButton) findViewById(R.id.buttonC);
        this.buttonD = (RadioButton) findViewById(R.id.buttonD);
        this.QuizText = (TextView) findViewById(R.id.QuizText);
        this.timeText = (TextView) findViewById(R.id.timeText);
        this.resultText = (TextView) findViewById(R.id.resultText);
        this.coinText = (TextView) findViewById(R.id.coinText);
        this.playercorrect = MediaPlayer.create(this, R.raw.correct);
        this.playerwrong = MediaPlayer.create(this, R.raw.wrong);
        this.fontone = Typeface.createFromAsset(getAssets(), "fonts/font_two.ttf");
        this.fonttwo = Typeface.createFromAsset(getAssets(), "fonts/main-Bold.otf");
        this.QuizText.setTypeface(this.fonttwo);
        this.questionText.setTypeface(this.fontone);
        this.buttonA.setTypeface(this.fontone);
        this.buttonB.setTypeface(this.fontone);
        this.buttonC.setTypeface(this.fontone);
        this.buttonD.setTypeface(this.fontone);
        this.timeText.setTypeface(this.fontone);
        this.resultText.setTypeface(this.fontone);
        this.coinText.setTypeface(this.fontone);
        this.JAVAQuizHelper = new QuizHelper(this);
        this.JAVAQuizHelper.getWritableDatabase();
        if (this.JAVAQuizHelper.getAllOfTheQuestions().size() == 0) {
            this.JAVAQuizHelper.allQuestion();
        }
        this.list = this.JAVAQuizHelper.getAllOfTheQuestions();
        Collections.shuffle(this.list);
        this.currentQuestion = this.list.get(this.qid);
        this.countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView textView = GameActivity.this.timeText;
                textView.setText(String.valueOf(GameActivity.this.timeValue) + "\"");
                GameActivity gameActivity = GameActivity.this;
                gameActivity.timeValue = gameActivity.timeValue + -1;
                if (GameActivity.this.timeValue == -1) {
                    GameActivity.this.resultText.setText(GameActivity.this.getString(R.string.timeup));
                    GameActivity.this.disableButton();
                }
            }

            public void onFinish() {
                GameActivity.this.timeUp();
            }
        }.start();
        updateQueAndOptions();
    }

    public void updateQueAndOptions() {
        this.questionText.setText(this.currentQuestion.getQuestion());
        this.buttonA.setText(this.currentQuestion.getOptA());
        this.buttonB.setText(this.currentQuestion.getOptB());
        this.buttonC.setText(this.currentQuestion.getOptC());
        this.buttonD.setText(this.currentQuestion.getOptD());
        this.timeValue = 20;
        this.countDownTimer.cancel();
        this.countDownTimer.start();
        this.coinText.setText(String.valueOf(this.coinValue));
        this.coinValue += 5;
    }

    public void buttonA(View view) {
        if (this.currentQuestion.getOptA().equals(this.currentQuestion.getAnswer())) {
            this.buttonA.setBackgroundResource(R.drawable.option_bg);
            if (this.qid < this.list.size() - 1) {
                disableButton();
                correctDialog();
                this.buttonA.setChecked(false);
                return;
            }
            gameWon();
            this.buttonA.setChecked(false);
            return;
        }
        gameLostPlayAgain();
        this.buttonA.setChecked(false);
        this.playerwrong.start();
    }

    public void buttonB(View view) {
        if (this.currentQuestion.getOptB().equals(this.currentQuestion.getAnswer())) {
            this.buttonB.setBackgroundResource(R.drawable.option_bg);
            if (this.qid < this.list.size() - 1) {
                disableButton();
                correctDialog();
                this.buttonB.setChecked(false);
                return;
            }
            gameWon();
            this.buttonB.setChecked(false);
            return;
        }
        gameLostPlayAgain();
        this.buttonB.setChecked(false);
        this.playerwrong.start();
    }

    public void buttonC(View view) {
        if (this.currentQuestion.getOptC().equals(this.currentQuestion.getAnswer())) {
            this.buttonC.setBackgroundResource(R.drawable.option_bg);
            if (this.qid < this.list.size() - 1) {
                disableButton();
                correctDialog();
                this.buttonC.setChecked(false);
                return;
            }
            gameWon();
            this.buttonC.setChecked(false);
            return;
        }
        gameLostPlayAgain();
        this.buttonC.setChecked(false);
        this.playerwrong.start();
    }

    public void buttonD(View view) {
        if (this.currentQuestion.getOptD().equals(this.currentQuestion.getAnswer())) {
            this.buttonD.setBackgroundResource(R.drawable.option_bg);
            if (this.qid < this.list.size() - 1) {
                disableButton();
                correctDialog();
                this.buttonD.setChecked(false);
                return;
            }
            gameWon();
            this.buttonD.setChecked(false);
            return;
        }
        gameLostPlayAgain();
        this.buttonD.setChecked(false);
        this.playerwrong.start();
    }

    public void gameWon() {
        Intent intent = new Intent(this, WonActivity.class);
        intent.putExtra("mytext", this.coinText.getText().toString());
        startActivity(intent);
        finish();
    }

    public void gameLostPlayAgain() {
        startActivity(new Intent(this, GameOverActivity.class));
        finish();
    }

    public void timeUp() {
        startActivity(new Intent(this, TimeOverActivity.class));
        finish();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.countDownTimer.start();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.countDownTimer.cancel();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.countDownTimer.cancel();
    }

    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(this);
        dialogCorrect.requestWindowFeature(1);
        if (dialogCorrect.getWindow() != null) {
            dialogCorrect.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();
        this.playercorrect.start();
        onPause();
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);
        ((TextView) dialogCorrect.findViewById(R.id.correctText)).setTypeface(this.fonttwo);
        buttonNext.setTypeface(this.fonttwo);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialogCorrect.dismiss();
                GameActivity.this.qid++;
                GameActivity gameActivity = GameActivity.this;
                gameActivity.currentQuestion = gameActivity.list.get(GameActivity.this.qid);
                GameActivity.this.updateQueAndOptions();
                GameActivity.this.resetColor();
                GameActivity.this.enableButton();
            }
        });
    }

    public void resetColor() {
        this.buttonA.setBackgroundResource(R.drawable.white_option_bg);
        this.buttonB.setBackgroundResource(R.drawable.white_option_bg);
        this.buttonC.setBackgroundResource(R.drawable.white_option_bg);
        this.buttonD.setBackgroundResource(R.drawable.white_option_bg);
    }

    public void disableButton() {
        this.buttonA.setEnabled(false);
        this.buttonB.setEnabled(false);
        this.buttonC.setEnabled(false);
        this.buttonD.setEnabled(false);
    }

    public void enableButton() {
        this.buttonA.setEnabled(true);
        this.buttonB.setEnabled(true);
        this.buttonC.setEnabled(true);
        this.buttonD.setEnabled(true);
    }

    public void setPlayer(MediaPlayer player) {
        player.setLooping(true);
        player.setVolume(100.0f, 100.0f);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        this.playercorrect.start();
        this.playerwrong.start();
        return 1;
    }
}
