package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private CharSequence[] tabTitle = {"お店一覧","追加"};
//    //Fragment
//    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {//中身を外部関数化する
//        @Override
//        public Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return new Tab_01Fragment();//実装
//                case 1:
//                    return new Tab_02Fragment();//実装
////                case 2:
////                    return new Tab_01Fragment();//実装
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabTitle[position];
//        }
//
//        @Override
//        public int getCount() {
//            return tabTitle.length;
//        }
//    };
    //外部化
    MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null){
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.linearLayout,new Tab_01Fragment());
//            transaction.add(R.id.linearLayout,new Tab_01Fragment());
//            transaction.commit();
//        }


        //Tab初期化
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setOffscreenPageLimit(tabTitle.length);
//        viewPager.setAdapter(adapter);

        final ViewPager viewPager = tabUpdate();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.destroyItem(viewPager,0,adapter.instantiateItem(viewPager,0));
                adapter.instantiateItem(viewPager,0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);

    }

    public ViewPager tabUpdate (){
        //Tab初期化
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setOffscreenPageLimit(tabTitle.length);
        viewPager.setAdapter(adapter);
        return viewPager;
    }
    private void updateFragment(ViewPager pager){
        adapter.destroyItem(pager,1,null);
        adapter.notifyDataSetChanged();
        pager.setAdapter(adapter);
    }
}
