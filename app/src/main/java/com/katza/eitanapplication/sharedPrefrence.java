package com.katza.eitanapplication;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sharedPrefrence extends AppCompatActivity implements View

        .OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_prefrence);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sp = getSharedPreferences("details1" , 0);
        initViews();
        String strfname = sp.getString("fname" , null);
        String strlname = sp.getString("lname" , null);
        if (strlname != null && strfname != null) {
            tvDisplay.setText("welcome " + strfname + " " + strlname);
        }
    }

    SharedPreferences sp;
    Button btnSave;
    EditText etFname, etLname;
    TextView tvDisplay;
    Dialog d;
    EditText eUsername , etPass;
    Button btnCustomLogin , btnLogin;
    private void initViews() {
        btnSave = (Button)findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(this);
        etFname = (EditText)findViewById(R.id.etFname);
        etLname = (EditText)findViewById(R.id.etLname);
        tvDisplay = (TextView)findViewById(R.id.tvDisplay);

    }

    public void onClick (View v) {
        if (btnSave == v) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("fname" ,etFname.getText().toString());
            editor.putString("lname" ,etLname.getText().toString());
            editor.commit();
        }
    }

}