package com.example.testfirebase.RecycleViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.testfirebase.Object.ImageSliderHome;
import com.example.testfirebase.R;

import java.util.List;

public class ImageSliderHomeAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageSliderHome> listImgHome;

    public ImageSliderHomeAdapter(Context mContext, List<ImageSliderHome> listImgHome) {
        this.mContext = mContext;
        this.listImgHome = listImgHome;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.auto_slider_img_home, container, false);
        ImageView imgHome = view.findViewById(R.id.imgSlider_Home);

        ImageSliderHome img = listImgHome.get(position);
        if(img != null)
            Glide.with(mContext).load(img.getResourceID()).into(imgHome);

        //them view vua moi custom vao viewgroup
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(listImgHome != null)
            return listImgHome.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //xoa view
        container.removeView((View) object);
    }
}
