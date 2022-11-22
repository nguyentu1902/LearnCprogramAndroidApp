package com.example.testfirebase.RecycleViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.testfirebase.FragmentOnBoard.Fragment1Onboard;
import com.example.testfirebase.FragmentOnBoard.Fragment2Onboard;
import com.example.testfirebase.FragmentOnBoard.Fragment3Onboard;
import com.example.testfirebase.FragmentOnBoard.Fragment4Onboard;
import com.example.testfirebase.FragmentOnBoard.Fragment5Onboard;
import com.example.testfirebase.FragmentOnBoard.Fragment6Onboard;

public class OnBoardAdapter extends FragmentStatePagerAdapter {

    public OnBoardAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Fragment1Onboard();

            case 1:
                return new Fragment2Onboard();

            case 2:
                return new Fragment3Onboard();

            case 3:
                return new Fragment4Onboard();

            case 4:
                return new Fragment5Onboard();

            case 5:
                return new Fragment6Onboard();

            default:
                return new Fragment1Onboard();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
