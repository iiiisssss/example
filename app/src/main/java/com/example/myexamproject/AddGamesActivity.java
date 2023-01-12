package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Game;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class AddGamesActivity extends AppCompatActivity implements View.OnClickListener {
    //组件定义
    private EditText etGameid;
    private EditText etGamename;
    private EditText etGametime;
    private EditText etGamenote;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        etGameid=(EditText)findViewById(R.id.et_gameid);
        etGamename = (EditText) findViewById(R.id.et_gamename);
        etGametime = (EditText) findViewById(R.id.et_gametime);
        etGamenote = (EditText) findViewById(R.id.et_gamenote);

        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String gameid=etGameid.getText().toString().trim();
        String gamename = etGamename.getText().toString().trim();
        String gametime = etGametime.getText().toString().trim();
        String gamenote = etGamenote.getText().toString();


        //检验信息是否正确
        if (TextUtils.isEmpty(gameid)) {
            Toast.makeText(this, "请输入游戏id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gamename)) {
            Toast.makeText(this, "请输入游戏名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gametime)) {
            Toast.makeText(this, "请输入约玩时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gamenote)) {
            Toast.makeText(this, "请输入备注", Toast.LENGTH_SHORT).show();
            return;
        }

        //添加信息
        Game game =new Game();
        game.gameid= gameid;
        game.gamename = gamename;
        game.gametime = gametime;
        game.gamenote= gamenote;

        //创建数据库访问对象
        MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        long result = dao.addGames(game);

        if (result > 0) {
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
        finish();

    }
}