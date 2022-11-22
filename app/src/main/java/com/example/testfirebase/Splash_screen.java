package com.example.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfirebase.FragmentOnBoard.OnBoard;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class Splash_screen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    private ProgressBar progressBar_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        mapping();
        progressBar_splash.setIndeterminateDrawable(new ThreeBounce());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_screen.this, OnBoard.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void mapping()
    {
        progressBar_splash = findViewById(R.id.progress_Splash);
    }
}