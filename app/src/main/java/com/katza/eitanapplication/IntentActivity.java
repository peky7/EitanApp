package com.katza.eitanapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentActivity extends AppCompatActivity {

    // רכיבים שנשארו מהמקור
    private EditText etName;
    private CheckBox cbIsStudent;
    private Button btnSubmit;

    // רכיבים חדשים עבור הגיל
    private Button btnOpenAgeCalc;
    private TextView tvAgeDisplay;

    // משתנה עזר לשמירת הגיל שחושב
    private String savedAge = "";

    // המנגנון שמקבל את הגיל מדף חישוב השנה
    private ActivityResultLauncher<Intent> ageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // מקבלים את הגיל כ-int וממירים ל-String
                        int ageResult = result.getData().getIntExtra("RESULT_AGE", 0);
                        savedAge = String.valueOf(ageResult);

                        // מציגים אותו ב-TextView
                        tvAgeDisplay.setText("הגיל שלך: " + savedAge);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. קישור הרכיבים
        etName = findViewById(R.id.etName);
        cbIsStudent = findViewById(R.id.cbIsStudent);
        btnSubmit = findViewById(R.id.btnSubmit);

        // קישור הרכיבים החדשים של הגיל
        btnOpenAgeCalc = findViewById(R.id.btnOpenAgeCalc);
        tvAgeDisplay = findViewById(R.id.tvAgeDisplay);

        // 2. לוגיקה לכפתור "הכנס שנת לידה"
        btnOpenAgeCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // פותח את הדף שיצרנו קודם (AgeCalculatorActivity)
                Intent intent = new Intent(IntentActivity.this, AgeCalculatorActivity.class);
                ageLauncher.launch(intent);
            }
        });

        // 3. לוגיקה לכפתור SUBMIT (המקורי)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // בדיקה שהמשתמש באמת חישב את הגיל לפני שממשיכים
                if (savedAge.isEmpty()) {
                    Toast.makeText(IntentActivity.this, "נא לחשב גיל תחילה", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(IntentActivity.this, ResultActivity.class);

                String name = etName.getText().toString();
                boolean isStudent = cbIsStudent.isChecked();

                // מעבירים את הנתונים (הגיל מגיע מהמשתנה savedAge)
                intent.putExtra("USER_NAME", name);
                intent.putExtra("USER_AGE", savedAge);
                intent.putExtra("IS_STUDENT", isStudent);

                startActivity(intent);
            }
        });
    }
}