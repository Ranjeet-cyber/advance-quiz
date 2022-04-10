package com.example42041.quizzerforcomputer;

import android.app.Activity;

public class QuestionActivity extends Activity {
    private String answer;

    /* renamed from: id */
    private int f33id;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String question;

    public QuestionActivity(String q, String oa, String ob, String oc, String od, String ans) {
        this.question = q;
        this.opta = oa;
        this.optb = ob;
        this.optc = oc;
        this.optd = od;
        this.answer = ans;
    }

    public QuestionActivity() {
        this.f33id = 0;
        this.question = BuildConfig.APPLICATION_ID;
        this.opta = BuildConfig.APPLICATION_ID;
        this.optb = BuildConfig.APPLICATION_ID;
        this.optc = BuildConfig.APPLICATION_ID;
        this.optd = BuildConfig.APPLICATION_ID;
        this.answer = BuildConfig.APPLICATION_ID;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getOptA() {
        return this.opta;
    }

    public String getOptB() {
        return this.optb;
    }

    public String getOptC() {
        return this.optc;
    }

    public String getOptD() {
        return this.optd;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setId(int i) {
        this.f33id = i;
    }

    public void setQuestion(String q1) {
        this.question = q1;
    }

    public void setOptA(String o1) {
        this.opta = o1;
    }

    public void setOptB(String o2) {
        this.optb = o2;
    }

    public void setOptC(String o3) {
        this.optc = o3;
    }

    public void setOptD(String o4) {
        this.optd = o4;
    }

    public void setAnswer(String ans) {
        this.answer = ans;
    }
}
