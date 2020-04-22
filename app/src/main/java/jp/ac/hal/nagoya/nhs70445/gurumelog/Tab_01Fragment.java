package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab_01Fragment extends Fragment {

//    private SQLiteDatabase mydb;
    //配列の宣言
    //String[] aryList;

    View rootView;
    View mainView;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        rootView = inflater.inflate(R.layout.fragment_tab_01,container,false);
        mainView = inflater.inflate(R.layout.activity_main,container,false);
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
//        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
//        ViewPager viewPager = (ViewPager) mainView.findViewById(R.id.viewPager);
//        Intent intent = getActivity().getIntent();
//        adapter.destroyItem(viewPager, 0, adapter.instantiateItem(viewPager, 0));
//        adapter.instantiateItem(viewPager, 0);
        dbList();
//        //更新ボタンリスナー
//        Button btnReset = (Button) rootView.findViewById(R.id.btn);
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbList();
//            }
//        });
//
    }

    @Override
    public void onViewCreated(View view,Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        dbList();
    }
    public void dbList() {
        SQLiteDatabase mydb;
        DBHelper dbhelp = new DBHelper(getActivity());
        mydb = dbhelp.getWritableDatabase();
        Cursor db_row;
//        db_row = mydb.query("t_user",
//                new String[]{"id","name","age"},
//                null,//whereの先name like ?
//                null,null,null,null);
        db_row = mydb.query("t_rest",
                new String[]{"id","name","place","genre","detail"},
                null,//whereの先name like ?
                null,null,null,null);
        String strResult = "";

        if(db_row.getCount() == 0){
            //データなし
            strResult += "データなし";
        }else{
            //データあり
            strResult += "データ"+db_row.getCount()+"件あり";
        }
        TextView txtvResult = (TextView)rootView.findViewById(R.id.txtRows);
        txtvResult.setText(strResult);

        ListView listView = (ListView)getActivity().findViewById(R.id.listView1);

        //データの格納
        ArrayList<HashMap<String,String>> listData = new ArrayList<>();
        while (db_row.moveToNext()){
            HashMap<String,String> data = new HashMap<>();
            data.put("id",db_row.getString(db_row.getColumnIndex("id")));
            data.put("name",db_row.getString(db_row.getColumnIndex("name")));
            data.put("place",db_row.getString(db_row.getColumnIndex("place")));
            data.put("genre",db_row.getString(db_row.getColumnIndex("genre")));
            data.put("detail",db_row.getString(db_row.getColumnIndex("detail")));
            listData.add(data);
        }
//        while (db_row.moveToNext()){
//            HashMap<String,String> data = new HashMap<>();
//            data.put("id",db_row.getString(db_row.getColumnIndex("id")));
//            data.put("name",db_row.getString(db_row.getColumnIndex("name")));
//            data.put("age",db_row.getString(db_row.getColumnIndex("age")));
//            listData.add(data);
//        }

        //アダプターの生成
        //リストビューへ連携するアダプター生成
        SimpleAdapter simpleAdapter
                = new SimpleAdapter(getActivity(),
                listData,//使用するデータ(ArrayList<>)
                R.layout.list_01,//データを入れ込むレイアウト
                new String[]{"name","place","genre","detail"},//ハッシュマップのid
                new int[]{R.id.txtName,R.id.txtPlace,R.id.txtGenre,R.id.txtDetail});//xmlのid
//        SimpleAdapter simpleAdapter
//                = new SimpleAdapter(getActivity(),
//                listData,//使用するデータ(ArrayList<>)
//                R.layout.list_01,//データを入れ込むレイアウト
//                new String[]{"id","name","age"},//
//                new int[]{R.id.txtName,R.id.txtPlace,R.id.txtGenre});
        //表示を設定
        listView.setAdapter(simpleAdapter);

        simpleAdapter.notifyDataSetChanged();

        onItemClick(listView);

    }

    public void onItemClick(ListView lv){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),SubActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });
    }

    public void dataSetChanged(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
    }

}
