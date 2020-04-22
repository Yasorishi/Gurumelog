package jp.ac.hal.nagoya.nhs70445.gurumelog;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by Yasushi on 2020/03/03.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private CharSequence[] tabTitle = {"お店一覧","追加"};
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Tab_01Fragment();//実装
            case 1:
                return new Tab_02Fragment();//実装
//                case 2:
//                    return new Tab_01Fragment();//実装
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

    @Override
    public int getCount() {return tabTitle.length;    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
