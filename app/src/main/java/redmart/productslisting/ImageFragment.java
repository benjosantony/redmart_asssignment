package redmart.productslisting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    String imageUrl = "";
    public static final String TAG = ImageFragment.class.getSimpleName();

    public static ImageFragment getInstance(String imageUrl) {
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.imageUrl = imageUrl;
        return imageFragment;
    }

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());


        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        layout.setGravity(Gravity.CENTER);
        layout.addView(imageView);
        Picasso.with(getActivity()).load(imageUrl).into(imageView, new Callback() {


            @Override
            public void onSuccess() {
                Log.e(TAG, "Successully loaded image");
            }

            @Override
            public void onError() {
                Log.e(TAG, "Unable to load image");
            }
        });


        return layout;


    }


}


