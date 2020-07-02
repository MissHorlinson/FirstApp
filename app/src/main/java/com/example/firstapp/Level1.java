package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;  // Left picture
    public int numRight;  // Right picture
    Array array = new Array();
    Random random = new Random();
    public int count = 0;  // Right answer count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // For level number
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);  // Set level

        // Left image corner
        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        // Right image corner
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        // Left TextView path
        final TextView textLeft = findViewById(R.id.text_left);

        // Right TextView path
        final TextView textRight = findViewById(R.id.text_right);

        // FullScreen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Start dialog
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  // no title
        dialog.setContentView(R.layout.previewdialog);  // dialog view path
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);  // can not close dialog by 'Back' button

        // Close dialog window button
        TextView btnClose = (TextView)dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Back to levels window
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) { }
                dialog.dismiss();  // Close dialog
            }
        });

        // Continue button
        Button btnContinue = (Button)dialog.findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss(); // Close dialog window
                } catch (Exception ex) { }
            }
        });

        dialog.show();  // Show dialog window
        //_________________________________________________________________________
        // End dialog
        dialogEnd = new Dialog(this); // create new dialog window
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);  // no title
        dialogEnd.setContentView(R.layout.dialogend);  // dialog view path
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);  // can not close dialog by 'Back' button

        // Close dialog window button
        TextView btnClose2 = (TextView)dialogEnd.findViewById(R.id.btn_close);
        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    /// Back to levels window
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) { }
                dialogEnd.dismiss();  // Close dialog
            }
        });

        // Continue button
        Button btnContinue2 = (Button)dialogEnd.findViewById(R.id.btn_continue);
        btnContinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    try {
                        Intent intent = new Intent(Level1.this, Level2.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception ex) { }
                    dialogEnd.dismiss(); // Close dialog window
                } catch (Exception ex) { }
            }
        });
        //_________________________________________________________________________

        // Back button
        Button btnBack = (Button)findViewById(R.id.back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ex) { }
            }
        });

        // Game point
        final int[] progress = {R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20};

        // Animation
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alfa);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);  // Get picture from imgArr
        textLeft.setText(array.texts1[numLeft]);  // Get text from numArr

        numRight = random.nextInt(10);

        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images1[numRight]);  // Get picture from imgArr
        textRight.setText(array.texts1[numRight]);  // Get text from numArr

        // Left picture click
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);  // Lock right picture
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count++;
                        }
                        drawPoint(20, count);
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        drawPoint(19, count);
                    }
                    if (count == 20) {
                        // Level exit
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 1) { }
                         else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);  // Get picture from imgArr
                        img_left.startAnimation(a);
                        textLeft.setText(array.texts1[numLeft]);  // Get text from numArr

                        numRight = random.nextInt(10);

                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);  // Get picture from imgArr
                        img_left.startAnimation(a);
                        textRight.setText(array.texts1[numRight]);  // Get text from numArr

                        img_right.setEnabled(true);  // Unlock right picture
                    }
                }
                return true;
            }

            public void drawPoint(int point, int count) {
                // Draw progress bar grey
                for(int i = 0; i < point; i++) {
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points);
                }

                // Draw progress bar green
                for (int i = 0; i < count; i++) {
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_green);
                }
            }
        });

        // Right picture click
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);  // Lock left picture
                    if (numRight > numLeft) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numRight > numLeft) {
                        if (count < 20) {
                            count++;
                        }
                        drawPoint(20, count);
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                       drawPoint(19, count);
                    }
                    if (count == 20) {
                        // Level exit
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 1) { }
                         else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);  // Get picture from imgArr
                        img_left.startAnimation(a);
                        textLeft.setText(array.texts1[numLeft]);  // Get text from numArr

                        numRight = random.nextInt(10);

                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);  // Get picture from imgArr
                        img_left.startAnimation(a);
                        textRight.setText(array.texts1[numRight]);  // Get text from numArr

                        img_left.setEnabled(true);  // Unlock left picture
                    }
                }
                return true;
            }

            public void drawPoint(int point, int count) {
                // Draw progress bar grey
                for(int i = 0; i < point; i++) {
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points);
                }

                // Draw progress bar green
                for (int i = 0; i < count; i++) {
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_green);
                }
            }
        });
    }


    // system 'Back' button
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception ex) { }
    }
}
