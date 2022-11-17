package com.example.testfirebase.DetailAdvanceCourse;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testfirebase.R;

public class Search extends AppCompatActivity {
    private Toolbar toolbarDetailStudy;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        webView = findViewById(R.id.webView_ac_Search);
        webView.loadUrl("http://192.168.1.122:8089/AdvanceCours/Details/13");

        mappingId();
        setSupportActionBar(toolbarDetailStudy);
        toolbarDetailStudy.setTitle(getResources().getString(R.string.titleACsearch));
        toolbarDetailStudy.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void mappingId()
    {
        toolbarDetailStudy = findViewById(R.id.toolBar_DetailStudy);
    }
}