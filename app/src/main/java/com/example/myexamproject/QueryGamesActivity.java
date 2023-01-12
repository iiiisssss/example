package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myexamproject.utils.MySQLiteOpenHelper;

import java.util.List;
import java.util.Map;

public class QueryGamesActivity extends AppCompatActivity {
    //定义组件
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_games);
        setTitle("查看记录");
        //初始化界面
        initView();
    }

    private void initView() {
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        List<Map<String,Object>> mOrderData=dao.getAllGames();
        listView=(ListView)findViewById(R.id.lst_orders);
        String[] from={"gameid","gamename","gametime","gamenote"};
        int[] to={R.id.tv_lst_gameid,R.id.tv_lst_gamename,R.id.tv_lst_gametime,R.id.tv_lst_gamenote};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryGamesActivity.this,mOrderData,R.layout.item_list,from,to);
        listView.setAdapter(listItemAdapter);
        dao.close();
    }
}