package com.example.testfirebase.DetailRemind;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testfirebase.R;

public class DebugFixbug extends AppCompatActivity {
    private Toolbar toolbarDetailRemind;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_fixbug);

        webView = findViewById(R.id.webView_rm_debug_fixbug);
        webView.loadUrl("http://192.168.1.122:8089/Tips/Details/7");


        mappingId();
        setSupportActionBar(toolbarDetailRemind);
        toolbarDetailRemind.setTitle(getResources().getString(R.string.titleRMdebugfixbug));
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