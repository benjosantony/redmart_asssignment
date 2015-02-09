package redmart.productslisting.api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import redmart.productslisting.utilities.ApiConstants;

/**
 * Created by Ben on 8/2/15.
 */
public class RedMartAPI {
    private RequestQueue mRequestQueue = null;

    public RedMartAPI(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
    }

    public  static final String PRODUCTS_REQUEST_TAG = ProductsRequest.class.getSimpleName();
    public  static final String PRODUCT_REQUEST_TAG = "Product Request TAG";

    public void getProducts(int page, int pageSize, String uri, Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("page", String.valueOf(page)));
        params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
        params.add(new BasicNameValuePair("uri", String.valueOf(uri)));
        ProductsRequest productsRequest =new ProductsRequest(listener, errorListener, params);
        productsRequest.setTag(PRODUCTS_REQUEST_TAG);
        this.mRequestQueue.add(productsRequest);
    }


    public void getProduct(long id, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiConstants.getProductsDetailsUrl(id), null, listener, errorListener);
        jsonObjectRequest.setTag(PRODUCT_REQUEST_TAG);
        this.mRequestQueue.add(jsonObjectRequest);

    }

}
