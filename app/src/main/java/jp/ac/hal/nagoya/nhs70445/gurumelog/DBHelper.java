package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    //コンストラクタ
    public DBHelper(Context cn){
        super(cn,"Sample.db",null,1);
    }

    //オーバーライド(onCreate)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = "";

        //初期テーブル作成
//        strSQL = " drop table if exists t_user ";
//        db.execSQL(strSQL);
//        strSQL = " drop table if exists t_rest ";
//        db.execSQL(strSQL);


//        //テスト用(3つ)
//        strSQL = " create table t_user ";
//        strSQL += " ( ";
//        strSQL += "  id integer primary key autoincrement, ";
//        strSQL += "  name text, ";
//        strSQL += "  age integer ";
//        strSQL += " ) ";
//        db.execSQL(strSQL);


        //テスト用(店舗)
        strSQL = " create table ";
        strSQL += " if not exists t_rest ";
        strSQL += " ( ";
        strSQL += "  id integer primary key autoincrement, ";
        strSQL += "  name text, ";
        strSQL += "  place text, ";
        strSQL += "  genre text, ";
        strSQL += "  detail text ";
        strSQL += " ) ";
        db.execSQL(strSQL);

        //初期データ作成
//        String arySQL[] =
//                {" insert into t_user(name,age) values ('kata',39) ",
//                 " insert into t_user(name,age) values ('ichi',52) ",
//                 " insert into t_user(name,age) values ('ktok',38) "};
//        for (int i=0;i<arySQL.length;i++){
//            db.execSQL(arySQL[i]);
//        }
        String arySQL2[] =
                {" insert into t_rest(name,place,genre,detail) values ('レストランHAL','愛知県','フレンチ','行きつけです。') ",
                 " insert into t_rest(name,place,genre,detail) values ('Mドナルド','愛知県','ファストフード','おいしい') ",
                 " insert into t_rest(name,place,genre,detail) values ('Lドナルド','東京都','ファストフード','おいしい') ",
                 " insert into t_rest(name,place,genre,detail) values ('Kドナルド','奈良県','ファストフード','うまし！') ",
                 " insert into t_rest(name,place,genre,detail) values ('〇屋','愛知県','ファストフード','よく行く。') ",
                 " insert into t_rest(name,place,genre,detail) values ('Fドナルド','大阪府','ファストフード','おいしい') "};
        for (int i=0;i<arySQL2.length;i++){
            db.execSQL(arySQL2[i]);
        }


    }

    //オーバーライド(onUpgrade)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //今のところは書かない
    }

}
