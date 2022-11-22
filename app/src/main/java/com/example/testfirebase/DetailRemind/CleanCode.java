package com.example.testfirebase.DetailRemind;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testfirebase.R;

public class CleanCode extends AppCompatActivity {
    private Toolbar toolbarDetailRemind;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_code);

        webView = findViewById(R.id.webView_rm_cleancode);
        webView.loadUrl("http://192.168.1.122:8089/Tips/Details/6");


        mappingId();
        setSupportActionBar(toolbarDetailRemind);
        toolbarDetailRemind.setTitle(getResources().getString(R.string.titleRMcleancode));
        toolbarDetailRemind.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void mappingId()
    {
        toolbarDetailRemind = findViewById(R.id.toolBar_DetailRemind);
    }
}