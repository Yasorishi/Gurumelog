package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class Tab_02Fragment extends Fragment {

    private SQLiteDatabase mydb;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){

        final View v = inflater.inflate(R.layout.fragment_tab_02, container, false);
        final View vm = inflater.inflate(R.layout.activity_main,container,false);
        //Buttonリスナー
        Button btnIns = (Button) v.findViewById(R.id.btnInsert);
        btnIns.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dataInsert(v);
                tab01Update(vm);
            }
        });
        return v;
    }
    @Override
    public void onViewCreated(View view,Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
    }


    @Override
    public void onStart(){
        super.onStart();
    }

    public void dataInsert(View myView){
        String strName = "店名";
        String strPlace = "場所";
        String strGenre = "ジャンル";
        String strDetail = "詳細情報";

        try {
            //EditTextを利用可能に
            strName = ((EditText) myView.findViewById(R.id.edName)).getText().toString();
            strPlace = ((EditText) myView.findViewById(R.id.edPlace)).getText().toString();
            strGenre = ((EditText) myView.findViewById(R.id.edGenre)).getText().toString();
            strDetail = ((EditText) myView.findViewById(R.id.edDetail)).getText().toString();
        }catch (NullPointerException e){
            Log.e("ERROR","ぬるぽ");

        }
        if (strDetail.length()==0){
            strDetail = "未入力です。";
        }
        String strMsg = "";
        if(strName.length()>0&&strPlace.length()>0&&strGenre.length()>0) {
            //Insert機能
            DBHelper dbhelp = new DBHelper(getActivity());
            mydb = dbhelp.getWritableDatabase();

            //中に入れるデータを梱包
            ContentValues cv = new ContentValues();
            cv.put("Name", strName);
            cv.put("Place", strPlace);
            cv.put("Genre", strGenre);
            cv.put("Detail", strDetail);

            //Query実行
            mydb.insert("t_rest", null, cv);
            strMsg = "登録完了しました";
            Toast t = Toast.makeText(getActivity(), strMsg, Toast.LENGTH_SHORT);
            View v = t.getView();
            v.setBackgroundColor(Color.rgb(129,170,224));
            t.show();
        }else{
            strMsg = "上から3項目は入力必須です";
            Toast t = Toast.makeText(getActivity(), strMsg, Toast.LENGTH_SHORT);
            View v = t.getView();
            v.setBackgroundColor(Color.rgb(255,79,69));
            t.show();
//            Toast.makeText(getActivity(), strMsg, Toast.LENGTH_SHORT).show();
        }


    }
    public void tab01Update(View viewMain){
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        ViewPager viewPager = (ViewPager) viewMain.findViewById(R.id.viewPager);

        adapter.destroyItem(viewPager,0,adapter.instantiateItem(viewPager,0));
        adapter.instantiateItem(viewPager,0);

    }
}
