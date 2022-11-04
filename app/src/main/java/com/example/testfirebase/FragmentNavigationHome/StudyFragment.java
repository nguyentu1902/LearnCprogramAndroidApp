package com.example.testfirebase.FragmentNavigationHome;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testfirebase.FragmentStudy.StudyViewPagerAdapter;
import com.example.testfirebase.R;
import com.google.android.material.tabs.TabLayout;

public class StudyFragment extends Fragment {
    private TabLayout tabLayout_study;
    private ViewPager viewPager_study;
    private View viewStudy;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewStudy = inflater.inflate(R.layout.fragment_study, container, false);
        tabLayout_study = viewStudy.findViewById(R.id.tab_layout_study);
        viewPager_study = viewStudy.findViewById(R.id.view_pager_study);

        StudyViewPagerAdapter studyViewPagerAdapter = new StudyViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_study.setAdapter(studyViewPagerAdapter);
        tabLayout_study.setupWithViewPager(viewPager_study);

        setTabDivider();
        return viewStudy;
    }

    public void setTabDivider()
    {
        View root = tabLayout_study.getChildAt(0);
        if(root instanceof LinearLayout)
        {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.BLACK);
            drawable.setSize(2,1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }

}
