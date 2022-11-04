package com.example.testfirebase.DetailBasicCourse;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.testfirebase.R;

public class Array2 extends AppCompatActivity {
    private Toolbar toolbarDetailStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bc_activity_array2);

        mappingId();
        setSupportActionBar(toolbarDetailStudy);
        toolbarDetailStudy.setTitle(getResources().getString(R.string.titleBCArray2));
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