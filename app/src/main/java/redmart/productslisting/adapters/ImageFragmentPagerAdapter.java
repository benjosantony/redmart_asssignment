package redmart.productslisting.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;

import redmart.productslisting.ImageFragment;
import redmart.productslisting.models.Image;

/**
 * Created by Ben on 9/2/15.
 */
public class ImageFragmentPagerAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {

    private ArrayList<Image> images = new ArrayList<Image>();
    private FragmentManager fm;
    public ImageFragmentPagerAdapter(FragmentManager fm,ArrayList<Image> images) {
        super(fm);
        this.fm = fm;
        this.images = images;
    }

    public ImageFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.images = images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        // Technically should be based on the position indicator in the Image Model , but its just a demo
        ImageFragment imageFragment =  ImageFragment.getInstance(images.get(position).getImageUrl());
        return imageFragment;

    }

    @Override
    public int getIconResId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
