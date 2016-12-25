package mad.easychinese;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[];
    int NumofTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumofTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumofTabs = mNumofTabsumb;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            page1Fragment tab1 = new page1Fragment();
            return tab1;
        }else if (position == 1){
            page2Fragment tab2 = new page2Fragment();
            return tab2;
        }else{

            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumofTabs;
    }
}