package com.example.acere5_475.last;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    ViewPager viewPager1;
    TabLayout tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager1 = (ViewPager) findViewById(R.id.ViewPager1);
        tab1 = (TabLayout) findViewById(R.id.Tab1);


        //Membuat Tab menu Home
        TabLayout.Tab tabPertama = tab1.newTab();
        tabPertama.setText("Home");
        tab1.addTab(tabPertama, true);

        //Membuat Tab menu Profil
        TabLayout.Tab tabKedua = tab1.newTab();
        tabKedua.setText("Profil");
        tab1.addTab(tabKedua);


        TabLayout.Tab tabKetiga = tab1.newTab();
        tabKetiga.setText("Data");
        tab1.addTab(tabKetiga);

        pageAdapter adapter = new pageAdapter(getSupportFragmentManager(), tab1.getTabCount());
        viewPager1.setAdapter(adapter);
        tab1.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager1.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

