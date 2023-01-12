package com.example.myexamproject;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamproject.bean.Game;
import com.example.myexamproject.utils.MySQLiteOpenHelper;


public class DeleteGamesActivity extends AppCompatActivity  implements View.OnClickListener{


    private EditText etGameid;
    private Button btnSearch;
    private EditText etGamename;
    private EditText etGametime;
    private EditText etGamenote;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_games);
        initView();
    }

    private void initView() {
        etGameid=(EditText) findViewById(R.id.et_gameid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etGamename=(EditText)findViewById(R.id.et_gamename);
        etGametime=(EditText)findViewById(R.id.et_gametime);
        etGamenote=(EditText)findViewById(R.id.et_gamenote);
        btnDelete= (Button) findViewById(R.id.btn_delete);


        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnDelete.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_delete:
                deleteOrder();
                break;
        }
    }
    //查找借书信息
    private void searchOrder() {
        String studentid=etGameid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Game game=dao.getGames(studentid);
        etGamename.setText(game.gamename);
        etGametime.setText(game.gametime);
        etGamenote.setText(game.gamenote);

        dao.close();
    }
    private void deleteOrder() {
        Game game=new Game();
        game.gameid=etGameid.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        int result= dao.deletGames(game);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
