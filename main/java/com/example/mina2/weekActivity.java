package com.example.mina2;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.Calendar;
import java.util.List;

public class weekActivity extends AppCompatActivity {

    Calendar Cal;

    int now_year;
    int now_month;
    int now_week;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        //calendar인스턴스 설정
        Cal = Calendar.getInstance();

        ViewPager2 vpPager = findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new WeekFragmentAdapter(this);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(3);



        // 현재 연도, 월, 일 받기
        Intent intent = getIntent();
        now_year = intent.getIntExtra("year", -1);
        now_month = intent.getIntExtra("month", -1);
        now_week = intent.getIntExtra("week", -1);

        if (now_year == -1 || now_month == -1) {
            now_year = Calendar.getInstance().get(Calendar.YEAR);
            now_month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            now_week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
            day = Calendar.getInstance().get(Calendar.DATE);
        }
        ActionBar ab = getSupportActionBar();
        ab.setTitle(now_year + "년" + now_month + "월");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}