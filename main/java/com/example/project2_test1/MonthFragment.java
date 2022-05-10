package com.example.project2_test1;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthFragment extends Fragment {
    Calendar cal;
    ArrayList<String> dayList;
    //GridAdapter gridAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mParam1;
    private int mParam2;

    int year;
    int month;

    public MonthFragment(){}

    /*public MonthFragment(int year, int month) {
        // Required empty public constructor
        now_year=year;
        now_month=month;
    } */

    public static MonthFragment newInstance(int year, int month) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View monthView= inflater.inflate(R.layout.fragment_month, container, false);
        // 그리드뷰 객체 얻어옴
        GridView gridView = monthView.findViewById(R.id.gridview);
        dayList=new ArrayList<String>();
        cal = Calendar.getInstance();

//파라미터에 저장된 값을 넣어 cal설정
        cal.set(Integer.parseInt(String.valueOf(mParam1)), Integer.parseInt(String.valueOf(mParam2))-1, 1);

        //cal.set(year, month-1, 1); //이번달 1일 set
        int startday = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일
// 1일 전 요일들에 공백채우기
        for (int i = 1; i < startday; i++) {
            dayList.add("");
        }
//현재 월에 끝일 구하기
        setCalDate(cal.get(Calendar.MONTH) + 1);
//6행 맞추기위한 공백채우기
        for(int i=dayList.size();i<42; i++){ // 6행7열 (42)- 리스트항목 개수 빼면..
            dayList.add("");
        }

        ArrayAdapter<String>adapter
                = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_activated_1,
                        dayList ) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // 어떠한 방향이든 상관없이 그리드뷰로 화면을 꽉 채우기 위해 각 칸의 높이를
                        // 그리드뷰의 높이 / 6 으로 설정해주었다.
                        // 각 칸의 색은 흰색으로 표현하였으며, 각 칸의 텍스트를 가운데로 정렬하였다.
                        TextView tv = (TextView) super.getView(position, convertView, parent);
                        //tv.getLayoutParams().height = gridView.getHeight() / 6;
                        tv.setBackgroundColor(Color.WHITE);
                        int gridviewH = gridView.getHeight() / 6;
                        tv.setHeight(gridviewH-2);
                        return tv;
                    }
        };
        gridView.setAdapter(adapter);

/*/어댑터 연결
        gridAdapter = new GridAdapter(getActivity(), dayList);
        gridView.setAdapter(gridAdapter); // 어댑터를 그리스뷰 객체에 연결*/

// 토스트 메세지
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),
                        mParam1+"/"+mParam2+"/"+dayList.get(i),Toast.LENGTH_SHORT).show();
                //view.setBackgroundColor(Color.YELLOW); // 색깔 누적

            }
        });

        return monthView;
    }
    // 해당 월에 표시할 일 수
    private void setCalDate(int now_month){
        cal.set(Calendar.MONTH, now_month - 1);

        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add(String.valueOf(i+1));
        }
    }
}