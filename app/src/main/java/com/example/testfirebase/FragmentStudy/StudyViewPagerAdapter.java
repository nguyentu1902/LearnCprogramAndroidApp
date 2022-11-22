package com.example.testfirebase.FragmentStudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class StudyViewPagerAdapter extends FragmentStatePagerAdapter {


    public StudyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new BasicFragment();
            case 1:
                return new AdvancedFragment();
            case 2:
                return new DevCFragment();
            case 3:
                return new RemindFragment();
        }
        return new BasicFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Cơ Bản";
            case 1:
                return "Nâng Cao";
            case 2:
                return "Dev-C";
            case 3:
                return "Nhắc Nhở";
            default:
                return "Cơ Bản";
        }
    }
}
