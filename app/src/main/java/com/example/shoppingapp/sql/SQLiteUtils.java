package com.example.shoppingapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import com.example.shoppingapp.bean.BaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql的使用帮助类
 *
 * @author HB.yangzuozhe
 * @date 2021-03-03
 */
public class SQLiteUtils {
    Context mContext;
    MySQLiteOpenHelper mHelper;

    public SQLiteUtils(Context context) {
        mContext = context;
        mHelper = new MySQLiteOpenHelper(context, "ShoppingStory.db", null, 1);
    }

    /**
     * 打开可读写的数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    /**
     * 打开只读的数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }

    public <T extends BaseBean> void insertData(String table, T bean) {
        ArrayMap<String, String> map = new ArrayMap<>();
        if (bean == null) {
            return;
        }
        //当前这个商品在购物车的数量
        long number = queryNumberData(table, new String[]{bean.getTitle()});
        map.put("picture", bean.getPicture());
        map.put("title", bean.getTitle());
        map.put("price", bean.getPrice());
        //打开一个可读写的数据库
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //写入第一条数据
        for (String key : map.keySet()) {
            values.put(key, map.get(key));
        }
        //插入数据表中，
        // 第一个参数: 表示要插入的数据表，
        // 第二个参数: SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值
        // 第三个参数：ContentValues对象
        db.insert(table, null, values);
        values.clear();
        upNumberDate(table, number, bean.getTitle());
    }

    /**
     * 修改字段数据
     */
    public void upNumberDate(String table, long number, String title) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        db.update(table, values, "title=?", new String[]{title});
    }

    /**
     * 删除大于某个数的值
     *
     * @param table  表名
     * @param number 大于几
     * @param field  某个约束字段
     */
    public void deleteMoreThanData(String table, int number, String field) {
        SQLiteDatabase db = getWritableDatabase();
        //第一个参数String：需要操作的表名
        //第二个参数String：where选择语句, 选择哪些行要被删除, 如果为null, 就删除所有行;
        //第三个参数String[]： where语句的参数, 逐个替换where语句中的 "?" 占位符;
        //注意这里的第三个参数，这里只能在数组里面写一个数，否则如果写多个数的话， 会报错
        db.delete(table, field + " > ?", new String[]{String.valueOf(number)});
    }

    /**
     * 删除所有数据
     *
     * @param table 表
     */
    public void deleteAll(String table) {
        getWritableDatabase().delete(table, null, null);
    }

    /**
     * 删除和这个字段相同的行
     *
     * @param table 表
     * @param field 字段名
     */
    public void deleteData(String table, String field, String value) {
        getWritableDatabase().delete(table, field + "= ?", new String[]{value});
    }

    /**
     * 查所有数据
     *
     * @param keys  需要插的列名
     * @param table 表名
     * @return
     */
    public ArrayList<HashMap<String, String>> queryAllData(List<String> keys, String table) {
        SQLiteDatabase db = getReadableDatabase();
        final ArrayList<HashMap<String, String>> list = new ArrayList<>();

        Cursor cursor = db.query(table, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final HashMap<String, String> map = new HashMap<>();
                for (String key : keys) {
                    final String name = cursor.getString(cursor.getColumnIndex(key));
                    map.put(key, name);
                }
                list.add(0,map);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 查询数据
     * 查询，数据表中是否有名字相同的数据，如果id大于0表示表中有这个数据，那么我们就增加数量，就让number+1
     * 如果id 小于0 ，那么表示没有这个数据，我们就让这个number数量=1
     */
    private long queryNumberData(String table, String[] value) {
//        String table, String[] keys, String keySelect, String value
        SQLiteDatabase db = getReadableDatabase();
        // 第一个参数String：表名
        // 第二个参数String[]:要查询的列名
        // 第三个参数String：查询条件
        // 第四个参数String[]：查询条件的参数
        // 第五个参数String:对查询的结果进行分组
        // 第六个参数String：对分组的结果进行限制
        // 第七个参数String：对查询的结果进行排序
        Cursor cursor = db.query(table, new String[]{"id", "number"}, "title=?", value, null, null, null);
        int id = -1;
        long number = 1;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"));
                if (id > 0) {
                    number = cursor.getLong(cursor.getColumnIndex("number"));
                    break;
                }
                // 将光标移动到下一行，从而判断该结果集是否还有下一条数据
                //如果有则返回true，没有则返回false
            } while (cursor.moveToNext());

        }
        if (id > 0) {
            return number + 1;
        } else {
            return number;
        }
    }


    public void upDataDb() {
        mHelper.getWritableDatabase().getVersion();

    }


    /**
     * 关闭数据库，释放资源
     */
    public void closeDb() {
        SQLiteDatabase db = getWritableDatabase();
        //关闭当前数据库
        db.close();
    }

    /**
     * 删除数据库
     */
    public void deleteDb() {
        mContext.deleteDatabase("BookStory.db");
    }

    //也可将Cursor中的数据转为 ArrayList<Map<String, String>> 类型数据
    public ArrayList<Map<String, String>> cursor2list(Cursor cursor) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

        //遍历Cursor
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", cursor.getString(cursor.getColumnIndex("name")));
            map.put("id", cursor.getString(cursor.getColumnIndex("id")));
            list.add(map);
        }
        return list;
    }
}
