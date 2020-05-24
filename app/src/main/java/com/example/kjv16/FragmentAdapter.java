package com.example.kjv16;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter{

    List<Fragment>chapterFragments=new ArrayList<>();
    List<String>titles=new ArrayList<>();
    FragmentManager fragmentManager;

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragmentManager=fm;
    }

    public void addFrag(Fragment fragment, String title){
        chapterFragments.add(fragment);
        titles.add(title);
    }

    public void clearAll(){
        chapterFragments.clear();
        titles.clear();
        notifyDataSetChanged();
        if(fragmentManager.getFragments() !=null){
            fragmentManager.getFragments().clear();
        }
        notifyDataSetChanged();
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.chapterFragments.get(position);
    }

    @Override
    public int getCount() {
        return chapterFragments.size();
    }

}
