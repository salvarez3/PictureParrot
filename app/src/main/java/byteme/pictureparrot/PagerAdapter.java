package byteme.pictureparrot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by iamse on 11/07/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm){
        super(fm);
        // TODO Auto-generated method stub
    }

    @Override
    public Fragment getItem(int arg0){
        // TODO Auto-generated method stub

        switch (arg0){
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount(){
        // TODO Auto-generated method stub
        return 2;
    }
}
