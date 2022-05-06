package com.example.project2_test1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 vpPager = findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new MonthViewAdapter(this,4);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(52); //처음 시작 포지션을 52으로 설정 -> 시작 페이지 5월이 될수 있도록

        //
        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // 뷰 페이저의 각 페이지별로 년도, 월을 달리하여 액션바에 표시하는 코드이다.
                // 월에는 모듈러를 사용하여 12월이 지나면 1월이 되도록 설정하였고
                // 해당 월과 페이지의 position을 더한 값을 12로 나누어 년도를 표현하였다.
               // int year = Calendar.getInstance().get(Calendar.YEAR);
                int year = (position/12)+2018;
                int month = position%12+1;
                ActionBar ab = getSupportActionBar();
                ab.setTitle((year)+ "년 " + month + "월");

                /* j- 코드드
                int year = Calendar.getInstance().get(Calendar.YEAR);

                int month = Calendar.getInstance().get(Calendar.MONTH)+1;
                int ym = ((Calendar.getInstance().get(Calendar.MONTH)+position))/12;
                month = (Calendar.getInstance().get(Calendar.MONTH)+position)%12+1;
                ActionBar ab = ((MainActivity)getActivity()).getSupportActionBar();
                ab.setTitle((year+ym)+ "년 " + month + "월"); */

            }
        });


        // MonthFragment monfrag = new MonthFragment();

        //앱바 --> 해당 페이지의 연도, 달 받아서 출력해줘야됨
        //calendar인스턴스 설정
        //Calendar Cal = Calendar.getInstance();



        /*/ 현재 연도, 월, 일 받기
        Intent intent = getIntent();
        int now_year = intent.getIntExtra("year", -1);
        int now_month = intent.getIntExtra("month", -1);
       // int now_week = intent.getIntExtra("week", -1);

        if (now_year == -1 || now_month == -1) {
            now_year = Calendar.getInstance().get(Calendar.YEAR);
            now_month = Calendar.getInstance().get(Calendar.MONTH) + 1;
          //  now_week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
            int day = Calendar.getInstance().get(Calendar.DATE);
        }
        ActionBar ab = getSupportActionBar();
        ab.setTitle(now_year + "년" + now_month + "월");*/


    }

    // Q : fragment에서 연도, 월 정보받아서 앱바에 출력하기
       // ActionBar ab = getSupportActionBar();
       // ab.setTitle(monfrag.now_year+"년"+monfrag.now_month+"월");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}