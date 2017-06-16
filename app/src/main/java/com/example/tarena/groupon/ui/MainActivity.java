package com.example.tarena.groupon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tarena.groupon.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    //头部
    LinearLayout cityContainer;
    TextView tvCity; //显示城市名称
    ImageView ivAdd;
    View menuLayout;


    //中段

    @BindView(R.id.pullToRefresh_ListView_main)
    PullToRefreshListView ptrListView;
    ListView listView;
    List<String> datas;
    ArrayAdapter<String> adapter;
    //脚步
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListView();
    }
    private void initListHeaderIcons(View listHeaderIcons){

    }
    @OnClick(R.id.ll_header_left_container)
    public void jumpToCity(View view){
        Intent intent=new Intent(this,CityActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.iv_add_main)
    public void toggleMenu(View view){
        if(menuLayout.getVisibility()==View.VISIBLE){
            menuLayout.setVisibility(View.INVISIBLE);
        }else{
            menuLayout.setVisibility(View.VISIBLE);
        }}

    private void initListView() {
        listView = ptrListView.getRefreshableView();
        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datas);
      //为listview添加若干个头部
        LayoutInflater inflater=LayoutInflater.from(this);
        View listHeaderIcons=inflater.inflate(R.layout.inflate_item01_layout,listView,false);
        View listHeaderSquares =inflater.inflate(R.layout.header_list_square,listView,false);
        View listHeaderAds = inflater.inflate(R.layout.header_list_ads, listView, false);
        View listHeaderCategories = inflater.inflate(R.layout.header_list_categories,listView,false);
        View listHeaderRecommend = inflater.inflate(R.layout.inflate_jingxuan_andxihuan_layout,listView,false);
        listView.addHeaderView(listHeaderIcons);
        listView.addHeaderView(listHeaderSquares);
        listView.addHeaderView(listHeaderAds);
        listView.addHeaderView(listHeaderCategories);
        listView.addHeaderView(listHeaderRecommend);


        listView.setAdapter(adapter);
        //添加下来松手后的刷新
        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(0, "新增内容");
                        adapter.notifyDataSetChanged();
                        ptrListView.onRefreshComplete();
                    }
                }, 1500);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        datas.add("aaa");
        datas.add("bbb");
        datas.add("ccc");
        datas.add("ddd");
        datas.add("eee");
        datas.add("fff");
        datas.add("ggg");
        adapter.notifyDataSetChanged();

    }
}
