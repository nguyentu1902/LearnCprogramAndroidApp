package com.example.testfirebase.FragmentOnBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testfirebase.Login;
import com.example.testfirebase.R;
import com.example.testfirebase.RecycleViewAdapter.OnBoardAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.relex.circleindicator.CircleIndicator;

public class OnBoard extends AppCompatActivity {
    private TextView skip, back, next;
    private ViewPager viewPagerOnBoard;
    private CircleIndicator circleIndicatorOnBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mapping();
        checkCurrentUser();
        back.setVisibility(View.INVISIBLE);

        OnBoardAdapter onBoardAdapter = new OnBoardAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerOnBoard.setAdapter(onBoardAdapter);

        circleIndicatorOnBoard.setViewPager(viewPagerOnBoard);

        //bat su kien de an btn back hoac btn next
        viewPagerOnBoard.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 5)
                {
                    next.setVisibility(View.INVISIBLE);
                }
                else
                    next.setVisibility(View.VISIBLE);

                if(position == 0)
                {
                    back.setVisibility(View.INVISIBLE);
                }
                else
                    back.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPagerOnBoard.setCurrentItem(5);
            }
        });

        //next
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPagerOnBoard.getCurrentItem() < 5)
                {
                    viewPagerOnBoard.setCurrentItem(viewPagerOnBoard.getCurrentItem() + 1);
                }
            }
        });

        //back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPagerOnBoard.getCurrentItem() > 0)
                {
                    viewPagerOnBoard.setCurrentItem(viewPagerOnBoard.getCurrentItem() - 1);
                }
            }
        });
    }

    private void mapping()
    {
        skip = findViewById(R.id.txtSkip_onboard);
        back = findViewById(R.id.txtBack_onboard);
        next = findViewById(R.id.txtNext_onboard);
        viewPagerOnBoard = findViewById(R.id.view_pager_Onboard);
        circleIndicatorOnBoard = findViewById(R.id.circleIndicator_Onboard);
    }

    //luu thong tin dang nhap nguoi dung
    public void checkCurrentUser()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(OnBoard.this, Login.class));
        }
    }
}