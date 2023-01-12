package com.example.myexamproject.utils;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


import com.example.myexamproject.bean.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLiteOpenHelper {

    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public MySQLiteOpenHelper(Context context) {
        this.context = context;
    }

    //打开数据库方法
    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库方法
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    //添加发布信息内容
    public long addGames(Game game) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入值
        values.put("gameid", game.gameid);
        values.put("gamename", game.gamename);
        values.put("gametime", game.gametime);
        values.put("gamenote", game.gamenote);

        // 通过insert()方法插入数据库中
        return db.insert("tb_Game", null, values);
    }

    //删除发布信息
    public int deletGames(Game game) {
        return db.delete("tb_Game", "gameid=?", new String[]{String.valueOf(game.gameid)});
    }

    //修改发布信息
    public int updateGames(Game game) {
        ContentValues value = new ContentValues();
        value.put("gamename", game.gamename);
        value.put("gametime", game.gametime);
        value.put("gamenote", game.gamenote);
        return db.update("tb_Game", value, "gameid=?", new String[]{String.valueOf(game.gameid)});
    }

    //根据游戏id查找以发布内容
    @SuppressLint("Range")
    public Game getGames(String gameid) {
        Cursor cursor = db.query("tb_Game", null, "gameid=?", new String[]{gameid}, null, null, null);
        Game game = new Game();
        while (cursor.moveToNext()) {
            game.gameid = cursor.getString(cursor.getColumnIndex("gameid"));
            game.gamename = cursor.getString(cursor.getColumnIndex("gamename"));
            game.gametime = cursor.getString(cursor.getColumnIndex("gametime"));
            game.gamenote = cursor.getString(cursor.getColumnIndex("gamenote"));

        }
        return game;
    }

    //查找所有发布信息
    @SuppressLint("Range")
    public ArrayList<Map<String, Object>> getAllGames() {
        ArrayList<Map<String, Object>> listGames = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Game", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("gameid", cursor.getString(cursor.getColumnIndex("gameid")));
                map.put("gamename", cursor.getString(cursor.getColumnIndex("gamename")));
                map.put("gametime", cursor.getString(cursor.getColumnIndex("gametime")));
                map.put("gamenote", cursor.getString(cursor.getColumnIndex("gamenote")));

                listGames.add(map);
            }
            return listGames;
        }
    }
}