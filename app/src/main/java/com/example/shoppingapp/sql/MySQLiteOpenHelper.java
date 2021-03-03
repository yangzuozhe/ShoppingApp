package com.example.shoppingapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * sql帮助类
 *
 * @author HB.yangzuozhe
 * @date 2021-03-02
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     * 创建了一个名为Shopping的表
     * autoincrement 表示自增，每次插入一条新的数据，主键id都会+1
     */
    public static final String CREATE_BOOK = "create table Shopping(" +
            "id integer primary key autoincrement," +
            "picture text," +
            "title text UNIQUE," +
            "price real," +
            "number long)";

    /**
     * @param context 上下文
     * @param name    数据库的名称
     * @param factory 自定义的 Cursor
     * @param version 数据库版本号
     */
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 第一次创建数据库的时候才会调用这个方法
     * 调用 getWritableDatabase() 或者 getReadableDatabase() 获取实例的时候，如果数据库不存在就调用这个方法
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Log.i("YangDemo", "创建数据库成功");
    }

    /**
     * @param db         数据库
     * @param oldVersion 旧的数据库的版本数字
     * @param newVersion 新的数据库的版本数字
     *                   我们在外面更新数据的时候，新版本的数一定要大于旧版本的数
     *                   创建SQLiteOpenHelper子类对象的时候,必须传入一个version参数
     *                   该参数就是当前数据库版本, 只要这个版本高于之前的版本, 就会触发这个onUpgrade()方法，
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果存在 Book表就删除表 Book
        db.execSQL("drop table if exists Book");
        onCreate(db);
        Log.i("YangDemo", "更新数据库成功");
    }
}
