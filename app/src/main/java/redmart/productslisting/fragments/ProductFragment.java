package redmart.productslisting.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import redmart.productslisting.ProductsListingApplication;
import redmart.productslisting.R;
import redmart.productslisting.adapters.ProductsAdapter;
import redmart.productslisting.dao.ProductsDAO;
import redmart.productslisting.models.Product;
import redmart.productslisting.parsers.ProductsListParser;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link redmart.productslisting.fragments.ProductFragment.onProductFragmentInteractionListener}
 * interface.
 */
public class ProductFragment extends Fragment implements AbsListView.OnItemClickListener , ProductsAdapter.onProductsAdapterReachLastItemListener{

    private static final String TAG = ProductFragment.class.getSimpleName();
    private onProductFragmentInteractionListener mListener;
    ProductsListingApplication productsListingApplication;


    private static final int PAGE_SIZE  =  10;

    protected int page = 0;
    protected  int  totalCount =  0;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ProductsAdapter mAdapter;


    // No arguments for now as we do not have viewpagers and different tabs
    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ProductsAdapter(getActivity(),this);
        productsListingApplication = (ProductsListingApplication) getActivity().getApplication();


    }


    private void fetchProducts() {

        int lastPageIndex =  0;
        if (ProductsDAO.getProductsList().size() ==0){
            page = 0;
        } else {
            ++page;
            if (totalCount % PAGE_SIZE == 0)
                lastPageIndex =  (totalCount / PAGE_SIZE) -1;
            else
                lastPageIndex = totalCount/PAGE_SIZE;
        }


        if (page <= lastPageIndex && getActivity() != null){
            // Fetch the products as needed
            productsListingApplication.getRedMartAPI().getProducts(page,PAGE_SIZE,"red-wine",new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.e(TAG, jsonObject.toString());
                    ProductsListParser productsListParser = new ProductsListParser(jsonObject);
                    totalCount = productsListParser.totalCount();
                    page = productsListParser.page();
                    ArrayList<Product> products  = productsListParser.parseProducts();
                    ProductsDAO.appendToList(products);
                    mAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    // TODO to be implemented
                }
            });
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchProducts();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (onProductFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onProductFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        productsListingApplication.getmRequestQueue().cancelAll(TAG);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onProductItemClick(ProductsDAO.getProductsList().get(position).getId());
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onProductsAdapterReachLastItem() {
        fetchProducts();
    }

    /**
     * This is the general way of communicating to activity
     * An alternative way is to use  otto bus for handling events for bigger applications
     */
    public interface onProductFragmentInteractionListener {

        public void onProductItemClick(long id);
    }


}
