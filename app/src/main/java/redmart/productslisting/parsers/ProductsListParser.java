package redmart.productslisting.parsers;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import redmart.productslisting.models.Product;


public class ProductsListParser extends BaseParser {

    static final String KEY_NAME_PRODUCTS_LIST = "products";
    private static final String TAG = ProductsListParser.class.getSimpleName();

    public ProductsListParser(String response) {
        super(response);
    }

    public ProductsListParser(JSONObject jsonResults) {
        super(jsonResults);
    }

    public  ArrayList<Product> parseProducts() {

        if (super.jsonResults == null ) return null;
        try {
            JSONArray jsonProducts  = jsonResults.getJSONArray(KEY_NAME_PRODUCTS_LIST);
            ArrayList<Product> products = parseProducts(jsonProducts);
            return products;

        }catch (Exception e){
            return  null;
        }

    }

    public static ArrayList<Product> parseProducts(JSONArray jsonArrayProducts)  {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            for (int i = 0; i < jsonArrayProducts.length(); i++) {
                JSONObject jsonProduct = jsonArrayProducts.getJSONObject(i);
                Product  product = ProductParser.parse(jsonProduct);
                products.add(product);
            }
        }catch (JSONException e){
            Log.e(TAG,e.getMessage());
        }
        return products;

    }


}
