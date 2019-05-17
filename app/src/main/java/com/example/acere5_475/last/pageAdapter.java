package com.example.acere5_475.last;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public pageAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                Homee tab1 = new Homee();
                return tab1;
            case 1:
                Profil tab2 = new Profil();
                return tab2;
            case 2:
                Form tab3 = new Form();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}

