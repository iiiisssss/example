package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Game;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class UpdateGamesActivity extends AppCompatActivity implements View.OnClickListener{
    //组件定义
    private EditText etGameid;
    private EditText etGamename;
    private EditText etGametime;
    private EditText etGamenote;
    private Button btnSearch;
    private Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_games);
        initView();
    }

    private void initView() {
        etGameid = findViewById(R.id.et_gameid);
        btnSearch = findViewById(R.id.btn_search);
        etGamename = findViewById(R.id.et_gamename);
        etGametime = findViewById(R.id.et_gametime);
        etGamenote = findViewById(R.id.et_gamenote);
        btnEdit=  findViewById(R.id.btn_edit);


        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_edit:
                updateOrder();
                break;
        }
    }
    private void searchOrder() {
        String gameid=etGameid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Game game=dao.getGames(gameid);
        etGamename.setText(game.gamename);
        etGametime.setText(game.gametime);
        etGamenote.setText(game.gamenote);


        dao.close();
    }

    private void updateOrder() {
        Game game=new Game();
        game.gameid=etGameid.getText().toString().trim();
        game.gamename=etGamename.getText().toString().trim();
        game.gametime=etGametime.getText().toString().trim();
        game.gamenote=etGamenote.getText().toString().trim();

        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        long result= dao.updateGames(game);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
