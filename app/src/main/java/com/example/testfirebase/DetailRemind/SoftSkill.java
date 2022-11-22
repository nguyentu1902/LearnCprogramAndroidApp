package com.example.testfirebase.DetailRemind;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testfirebase.R;

public class SoftSkill extends AppCompatActivity {
    private Toolbar toolbarDetailRemind;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_skill);

        webView = findViewById(R.id.webView_rm_softskill);
        webView.loadUrl("http://192.168.1.122:8089/Tips/Details/4");

        mappingId();
        setSupportActionBar(toolbarDetailRemind);
        toolbarDetailRemind.setTitle(getResources().getString(R.string.titleRMsoftskill));
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