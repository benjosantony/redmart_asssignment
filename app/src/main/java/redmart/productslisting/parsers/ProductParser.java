package redmart.productslisting.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import redmart.productslisting.models.Image;
import redmart.productslisting.models.Price;
import redmart.productslisting.models.Product;
import redmart.productslisting.utilities.ParseUtils;


public class ProductParser extends BaseParser {


    private static final String KEY_NAME_ID = "id";
    private static final String KEY_NAME_TITLE = "title";
    private static final String KEY_NAME_DESC = "desc";
    private static final String KEY_NAME_PRICING = "pricing";
    private static final String KEY_NAME_MEASURE = "measure";
    private static final String KEY_NAME_PRODUCT_QTY = "wt_or_vol";

    private static final String KEY_NAME_IMAGES = "images";
    private static final String KEY_NAME_IMG = "img";


    /**
     * @param response
     */
    public ProductParser(String response) {
        super(response);
    }

    /**
     * @param jsonResults
     */
    public ProductParser(JSONObject jsonResults) {
        super(jsonResults);
    }

    public Product parse() {
        Product product = new Product();

        return product;
    }


    /**
     * @param jsonProduct
     * @return product
     */
    public static Product parse(JSONObject jsonProduct) {
        Product product = new Product();

        try {

            // ID
            if (jsonProduct.has(KEY_NAME_ID))
                product.setId(ParseUtils.getLong(jsonProduct.getString(KEY_NAME_ID)));
            // TITLE
            if (jsonProduct.has(KEY_NAME_TITLE))
                product.setTitle(jsonProduct.getString(KEY_NAME_TITLE));
            // DESC
            if (jsonProduct.has(KEY_NAME_DESC))
                product.setDesc(jsonProduct.getString(KEY_NAME_DESC));
            // VOLUME WEIGHT
            if (jsonProduct.has(KEY_NAME_MEASURE)) {
                JSONObject jsonObjectMeasure = jsonProduct.getJSONObject(KEY_NAME_MEASURE);
                if (jsonObjectMeasure.has(KEY_NAME_PRODUCT_QTY))
                    product.setVolumeWeight(jsonObjectMeasure.getString(KEY_NAME_PRODUCT_QTY));
            }
            // PRICE
            if (jsonProduct.has(KEY_NAME_PRICING)) {

                Price price = PricingParser.parsePricing(jsonProduct.getJSONObject(KEY_NAME_PRICING));
                product.setPrice(price);
            }
            // IMAGES
            if (jsonProduct.has(KEY_NAME_IMAGES)) {
                ArrayList<Image> images = ImageParser.parseImages(jsonProduct.getJSONArray(KEY_NAME_IMAGES));
                product.setImages(images);
            }
            // IMG
            if (jsonProduct.has(KEY_NAME_IMG)) {
                Image image = ImageParser.parseImage(jsonProduct.getJSONObject(KEY_NAME_IMG));
                product.setImg(image);
            }

        } catch (JSONException e) {
            // No way this exception  will occur
        }
        return product;
    }


    private static ArrayList<Image> parseImages(JSONArray jsonArrayImages) {
        ArrayList<Image> images = new ArrayList<>();
        return images;

    }

    private static Image parseImage(JSONObject jsonObjectImage) {
        Image image = new Image();


        return image;
    }

}
