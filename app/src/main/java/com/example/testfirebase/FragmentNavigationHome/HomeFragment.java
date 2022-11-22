package com.example.testfirebase.FragmentNavigationHome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.testfirebase.Object.ImageSliderHome;
import com.example.testfirebase.R;
import com.example.testfirebase.RecycleViewAdapter.ImageSliderHomeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {

    private View viewHome;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageSliderHomeAdapter imageSliderHomeAdapter;
    private List<ImageSliderHome> listImg;
    private Timer timeToSlide;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewHome = inflater.inflate(R.layout.fragment_home, container, false);

        mapping();
        webView.loadUrl("http://192.168.1.122:8089/Homes/Details/2");

        listImg = getListImg();
        imageSliderHomeAdapter = new ImageSliderHomeAdapter(getActivity(), listImg);
        viewPager.setAdapter(imageSliderHomeAdapter);

        circleIndicator.setViewPager(viewPager);
        imageSliderHomeAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImg();

        return viewHome;
    }

    private void mapping()
    {
        viewPager = viewHome.findViewById(R.id.viewPager_imgHome);
        circleIndicator = viewHome.findViewById(R.id.circlerIndicator_imgHome);
        webView = viewHome.findViewById(R.id.webView_Home);
    }

    private List<ImageSliderHome> getListImg()
    {
        List<ImageSliderHome> list = new ArrayList<>();
        list.add(new ImageSliderHome(R.drawable.home1));
        list.add(new ImageSliderHome(R.drawable.home2));
        list.add(new ImageSliderHome(R.drawable.home3));
        list.add(new ImageSliderHome(R.drawable.home4));
        list.add(new ImageSliderHome(R.drawable.home5));

        return list;
    }

    private void autoSlideImg()
    {
        if(listImg == null || listImg.isEmpty() || viewPager == null)
            return;

        //khoi tao timer
        if(timeToSlide == null)
            timeToSlide = new Timer();

        timeToSlide.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentTime = viewPager.getCurrentItem();
                        int totalItem = listImg.size() - 1;
                        if(currentTime < totalItem)
                        {
                            currentTime++;
                            viewPager.setCurrentItem(currentTime);
                        }
                        else
                            viewPager.setCurrentItem(0);
                    }
                });
            }
        }, 1500, 1500);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(timeToSlide != null)
        {
            timeToSlide.cancel();
            timeToSlide = null;
        }
    }
}
