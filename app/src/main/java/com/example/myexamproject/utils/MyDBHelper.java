package com.example.myexamproject.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    //常量定义
    public static final String name = "db_game1.db";
    public static final int DB_VERSION = 1;
    //创建表
    public static final String CREATE_USERDATA1 = "create table tb_Game(gameid char(10)primary key,gamename varchar(20),gametime varchar(20),gamenote varchar(20))";
    public MyDBHelper(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA1);
        db.execSQL("insert into tb_Game(gameid,gamename,gametime,gamenote)Values('974296731','王者荣耀','5.28下午','双排上分')");
        db.execSQL("insert into tb_Game(gameid,gamename,gametime,gamenote)Values('572633061','英雄联盟','5.29上午','寻找代练')");
        db.execSQL("insert into tb_Game(gameid,gamename,gametime,gamenote)Values('330443961','CSGO','5.29上午','组队开黑')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}