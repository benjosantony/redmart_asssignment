package redmart.productslisting.utilities;

/**
 * Created by Ben on 9/2/15.
 */
public class ApiConstants {

    public static final String BASE_URL = "https://api.redmart.com/v1.5";
    public static final String PRODUCTS_LIST_URL = BASE_URL + "/products/bycategory";

    public static  String getProductsDetailsUrl (long id){
        return BASE_URL + "/products/" + id;
    }


}
