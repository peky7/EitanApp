package com.katza.eitanapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Switch;
import android.widget.ImageView;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

    }

    Button btn1 , btn2 , btn3;
    Switch switch1;
    ImageView imageView;
    SeekBar seekBar;
    private void initViews () {
        /*btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hgh", Toast.LENGTH_SHORT).show();
            }
        });*/
        switch1 = findViewById(R.id.switch1);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        //imageView.setVisibility(View.GONE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageView.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "התמונה הוסתרה", Toast.LENGTH_SHORT).show();
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "התמונה הוצגה", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float alpha = progress / 100f;
                imageView.setAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // כשהמשתמש מתחיל לגרור את ה-SeekBar
                Toast.makeText(MainActivity.this, "התחלת להזיז את הסליידר", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // כשהמשתמש משחרר את ה-SeekBar
                Toast.makeText(MainActivity.this, "שחררת את הסליידר", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    /*@Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn1) {
            Toast.makeText(this , "Click BTN1" , Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.btn2) {
            Toast.makeText(this , "Click BTN2" , Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this , "Error" , Toast.LENGTH_SHORT).show();
        }
    }*/

}