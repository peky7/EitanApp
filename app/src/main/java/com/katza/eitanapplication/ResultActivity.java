package com.katza.eitanapplication;

import android.content.Intent; // חובה לייבא
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    // הגדרת המשתנים
    private TextView tvResult;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // הקוד לעיצוב מקצה לקצה (חשוב שיהיה תואם ל-XML עם id=main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. קישור לרכיבים ב-XML
        tvResult = findViewById(R.id.tvResult);
        btnOk = findViewById(R.id.btnOk);

        // 2. קבלת הנתונים מהמסך הקודם
        Intent intent = getIntent();

        if (intent != null) { // בדיקת בטיחות שה-Intent לא ריק
            String name = intent.getStringExtra("USER_NAME");
            String age = intent.getStringExtra("USER_AGE");
            boolean isStudent = intent.getBooleanExtra("IS_STUDENT", false);

            // לוגיקה: הפיכת true/false לטקסט "כן/לא"
            String studentStatus = isStudent ? "כן" : "לא";

            // בניית המחרוזת להצגה
            String resultText = "שלום: " + name + "\n\n" +
                    "גילך: " + age + "\n\n" +
                    "האם אתה תלמיד הנדסת תוכנה: " + studentStatus;

            tvResult.setText(resultText);
        }

        // 3. כפתור חזרה
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // סגירת המסך הנוכחי וחזרה למסך הקודם
                finish();
            }
        });
    }
}