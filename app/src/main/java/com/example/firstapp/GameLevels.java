package com.example.firstapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Back button
        Button btnBack = (Button)findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) { }
            }
        });

        //Level 1 button
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level >= 1) {
                        Intent intent = new Intent(GameLevels.this, Level1.class);
                        startActivity(intent);
                        finish();
                    } else { }
                } catch (Exception ex) { }
            }
        });

        //Level 2 button
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level >= 2) {
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);
                        finish();
                    } else { }
                } catch (Exception ex) { }
            }
        });

        //Level 3 button
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level >= 3) {
                        Intent intent = new Intent(GameLevels.this, Level3.class);
                        startActivity(intent);
                        finish();
                    } else { }
                } catch (Exception ex) { }
            }
        });

        //Level 4 button
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level >= 4) {
                        Intent intent = new Intent(GameLevels.this, Level4.class);
                        startActivity(intent);
                        finish();
                    } else { }
                } catch (Exception ex) { }
            }
        });

        //Level 5 button
        TextView textView5 = (TextView)findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                     if (level >= 5) {
                    Intent intent = new Intent(GameLevels.this, Level5.class);
                    startActivity(intent);
                    finish();
                     } else { }
                } catch (Exception ex) { }
            }
        });

        //Level 6 button
        TextView textView6 = (TextView)findViewById(R.id.textView6);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (level >= 5) {
                        inProcess();
                    } else { }
                } catch (Exception ex) { }
            }
        });

        final int[] x = {
                R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5,
                R.id.textView6, R.id.textView7, R.id.textView8, R.id.textView9, R.id.textView10,
                R.id.textView11, R.id.textView12, R.id.textView13, R.id.textView14, R.id.textView15,
                R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19, R.id.textView20,
                R.id.textView21, R.id.textView22, R.id.textView23, R.id.textView24, R.id.textView25,
                R.id.textView26, R.id.textView27, R.id.textView28,  R.id.textView29, R.id.textView30};

        for (int i = 1; i < level; i++) {
            TextView tv = findViewById(x[i]);
            tv.setText("" + (i + 1));
        }
    }

    public void inProcess() {
        // Dialog
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  // no title
        dialog.setContentView(R.layout.dialoginprocess);  // dialog view path
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);  // can not close dialog by 'Back' button

        // Close dialog window button
        TextView btnClose = (TextView) dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Back to levels window
                    //Intent intent = new Intent(GameLevels.this, GameLevels.class);
                   // startActivity(intent);
                   // finish();
                } catch (Exception ex) {
                }
                dialog.dismiss();  // Close dialog
            }
        });
        dialog.show();
    }
    // system 'Back' button
   @Override
   public void onBackPressed(){
       try {
           Intent intent = new Intent(GameLevels.this, MainActivity.class);
           startActivity(intent);
           finish();
       } catch (Exception ex) { }
   }
}
