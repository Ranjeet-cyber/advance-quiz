package com.example42041.quizzerforcomputer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class QuizHelper extends SQLiteOpenHelper {
    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE TQ ( _UID INTEGER PRIMARY KEY AUTOINCREMENT , QUESTION VARCHAR(255), OPTA VARCHAR(255), OPTB VARCHAR(255), OPTC VARCHAR(255), OPTD VARCHAR(255), ANSWER VARCHAR(255));";
    private static final String DB_NAME = "JavaQuiz.db";
    private static final int DB_VERSION = 3;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS TQ";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";
    private static final String OPTD = "OPTD";
    private static final String QUESTION = "QUESTION";
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private Context context;

    QuizHelper(Context context2) {
        super(context2, DB_NAME, (SQLiteDatabase.CursorFactory) null, 3);
        this.context = context2;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public void allQuestion() {
        ArrayList<QuestionActivity> arraylist = new ArrayList<>();
        arraylist.add(new QuestionActivity("JVM Stand for", "JAVA Runtime Environment ?", "Java Virtual machine", "Java Voice Machine", "Virtual Java Machine ",
                "Java Virtual machine"));
        arraylist.add(new QuestionActivity("Which method is the starting point for all Java programs ?", "public", "void", "main", "class",
                "main"));
        arraylist.add(new QuestionActivity("Which method print text in java ?", "out.writeText()", "System.out.println()", "print()", "print.out()",
                "System.out.println()"));
        arraylist.add(new QuestionActivity("Which symbol is used for Single Line Comment?", "** at the beginning of the line", "// at the end of the line", "*/ at the beginning of the line", "// at the beginning of the line",
                "// at the beginning of the line"));
        arraylist.add(new QuestionActivity("Which symbol is used for Java doc style comment?", "// at the beginning of the line", "/** and */to wrap a comment", "/* and */ to wrap comment", "// and */ to wrap a comment",
                "/** and */to wrap a comment"));
        addAllQuestions(arraylist);
    }

    private void addAllQuestions(ArrayList<QuestionActivity> allQuestions) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            Iterator<QuestionActivity> it = allQuestions.iterator();
            while (it.hasNext()) {
                QuestionActivity question = it.next();
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, (String) null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /* access modifiers changed from: package-private */
    public List<QuestionActivity> getAllOfTheQuestions() {
        List<QuestionActivity> questionsList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(TABLE_NAME, new String[]{UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        while (cursor.moveToNext()) {
            QuestionActivity question = new QuestionActivity();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
