package redmart.productslisting.fragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;

import java.util.ArrayList;

import redmart.productslisting.ProductsListingApplication;
import redmart.productslisting.R;
import redmart.productslisting.adapters.ImageFragmentPagerAdapter;
import redmart.productslisting.models.Image;
import redmart.productslisting.models.Price;
import redmart.productslisting.models.Product;
import redmart.productslisting.parsers.ProductParser;
import redmart.productslisting.utilities.FormatUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {
    ProductsListingApplication productsListingApplication;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private long id = 0;
    private String title = "";

    Product product;

    ViewPager mViewPager;
    CirclePageIndicator mCirclePageIndicator;
    ImageFragmentPagerAdapter mImageFragmentPagerAdapter;
    ProgressBar mProgressBar;
    View mMainContainer;

    TextView tvTitle;
    TextView tvQty;
    TextView tvPromo;
    TextView tvOriginalPrice;
    TextView tvPromotionalPrice;
    TextView tvDescription;



    public static ProductDetailsFragment newInstance(long id, String title) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ID, id);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        ActionBar ab = ((ActionBarActivity) getActivity()).getSupportActionBar();
        ab.setTitle(title);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            id = getArguments().getLong(ID);
            title = getArguments().getString(TITLE);
        }
        productsListingApplication = ProductsListingApplication.get();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =   inflater.inflate(R.layout.fragment_product_details, container, false);
        tvOriginalPrice = (TextView) view.findViewById(R.id.tvPrice);
        // Set the strike through
        tvOriginalPrice.setPaintFlags( tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvPromotionalPrice = (TextView)  view.findViewById(R.id.tvPromoPrice);
        tvPromo = (TextView)  view.findViewById(R.id.tvProductPromoText);
        tvQty = (TextView)  view.findViewById(R.id.tvProductQty);
        tvTitle = (TextView)  view.findViewById(R.id.tvProductTitle);
        tvDescription = (TextView)view.findViewById(R.id.tvDescription);

        mImageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager =  (ViewPager) view.findViewById(R.id.pager);
        mCirclePageIndicator =  (CirclePageIndicator)view.findViewById(R.id.indicator);
        mViewPager.setAdapter(mImageFragmentPagerAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);
        mProgressBar =  (ProgressBar)view.findViewById(R.id.progressBar);
        // Lets make it red
        mProgressBar.getIndeterminateDrawable().setColorFilter(0xFFcc0000, android.graphics.PorterDuff.Mode.SRC_ATOP);
        mMainContainer = view.findViewById(R.id.mainContainer);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hide();
        fetchData();

    }

    private void hide(){
        mProgressBar.setVisibility(View.VISIBLE);
        mMainContainer.setVisibility(View.INVISIBLE);
    }
    private void show(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mMainContainer.setVisibility(View.VISIBLE);
    }

    /**
     * To be called only after the view is set
     */
    private void fetchData() {


        productsListingApplication.getRedMartAPI().getProduct(id, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (getActivity() == null) return;
                ProductParser productParser = new ProductParser(jsonObject);
                product = productParser.parse();
                ArrayList<Image> images = product.getImages();
                mImageFragmentPagerAdapter.setImages(images);
                mImageFragmentPagerAdapter.notifyDataSetChanged();

                tvTitle.setText(product.getTitle());
                tvDescription.setText(product.getDesc());
                Price price = product.getPrice();
                String savingsText = price.getSavingsText();
                savingsText = price.isOnSale() ? savingsText : "SOLD OUT";
                tvPromo.setText(savingsText);
                if (price.isOnSale()){
                    tvPromo.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.promo_text_background));
                } else {
                    tvPromo.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.sold_out_text_background));
                }

                tvOriginalPrice.setText( "S$" + FormatUtils.getDollarString(price.getPrice()));
                tvPromotionalPrice.setText( "S$" + FormatUtils.getDollarString(price.getPromoPrice()));
                tvQty.setText(product.getVolumeWeight());


                show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // TODO handle the error
            }
        });
    }
}
