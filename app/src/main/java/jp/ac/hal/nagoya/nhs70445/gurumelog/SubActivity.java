package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {

    private SQLiteDatabase mydb;
    private String strId;//ListView上の位置(0から)
    private String strId2;//SQLite上のid(0から)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = this.getIntent();
        int intNum = intent.getIntExtra("id",0);
        TextView textView;
//        textView = (TextView)this.findViewById(R.id.textViewSub);//テスト用のTextView
//        strId = String.valueOf(intNum+1);
//        textView.setText(strId);
//        textView = (TextView)this.findViewById(R.id.textViewSub2);//テスト用のTextView
        DBHelper dbHelper = new DBHelper(this);
        mydb = dbHelper.getWritableDatabase();
        strId2 = dbReadId(intNum+1);
//        textView.setText(strId2);

        String[] strData = ReadDatas(strId2);
        textView = (TextView)this.findViewById(R.id.tvName);
        textView.setText(strData[0]);
        textView = (TextView)this.findViewById(R.id.tvPlace);
        textView.setText(strData[1]);
        textView = (TextView)this.findViewById(R.id.tvGenre);
        textView.setText(strData[2]);
        textView = (TextView)this.findViewById(R.id.tvDetail);
        textView.setText(strData[3]);

    }

    public void onStart(){
        super.onStart();
    }

    public String dbReadId(int intId){

        String strData = "";
        String strColumns[];
        Cursor db_row;

        String strSQL = "";
//        strSQL = " select t1.id,(select count(t2.id) from t_rest t2 where t1.id > t2.id) + 1 as 'rank' from t_rest t1 group by rank having rank = ? ";
        strSQL = " select ";
        strSQL += " t1.id, ";
        strSQL += " (select count(t2.id) ";
        strSQL += " from t_rest t2 ";
        strSQL += " where t1.id > t2.id) + 1 as 'rank' ";
        strSQL += " from t_rest t1 ";
//        strSQL += " group by rank ";
//        strSQL += " having rank = ? ";
        strSQL += " order by 'rank' ";
//        db_row = mydb.rawQuery(strSQL,new String[]{strId});
        db_row = mydb.rawQuery(strSQL,null);
        db_row.moveToFirst();
        while ( db_row.getInt(db_row.getColumnIndex("rank"))< intId){
            db_row.moveToNext();
        }
        strColumns = db_row.getColumnNames();
        strData = String.valueOf(db_row.getCount());
        strData = String.valueOf(db_row.getInt(db_row.getColumnIndex("id")));

//        if (db_row.moveToFirst()){
//        }
        db_row.close();

        return strData;
    }

    public String[] ReadDatas(String strId2){
        Cursor db_row;
        String aryId[] = {strId2};
        db_row = mydb.query("t_rest",new String[]{"id","name","place","genre","detail"},"id=?",aryId,null,null,null);
        db_row.moveToFirst();
        String[] data = {
                db_row.getString(db_row.getColumnIndex("name")),
                db_row.getString(db_row.getColumnIndex("place")),
                db_row.getString(db_row.getColumnIndex("genre")),
                db_row.getString(db_row.getColumnIndex("detail"))
        };

        return data;
    }

    public void dbDelete(View myView){

        mydb.delete("t_rest","id=?",new String[]{strId2});

        boolean delFlg = true;

        //SubActivityの終了
        Intent intentBack = new Intent(SubActivity.this,Tab_01Fragment.class);
        intentBack.putExtra("delFlg",delFlg);
        setResult(RESULT_OK,intentBack);
        //終了
        finish();
    }

    public void btnBack(View myView){
        finish();
    }

}
