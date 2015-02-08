package redmart.productslisting.parsers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import redmart.productslisting.models.Price;
import redmart.productslisting.utilities.ParseUtils;

/**
 * Created by Ben on 8/2/15.
 */
public class PricingParser extends BaseParser {

    private static String KEY_NAME_ON_SALE = "on_sale";
    private static String KEY_NAME_PRICE = "price";
    private static String KEY_NAME_PROMO_PRICE = "promo_price";
    private static String KEY_NAME_SAVINGS_TEXT = "savings_text";


    private static final String TAG = PricingParser.class.getSimpleName();

    public PricingParser(String response) {
        super(response);
    }

    public PricingParser(JSONObject jsonResults) {
        super(jsonResults);
    }

    public static Price parsePricing(JSONObject jsonObjectPricing){
        Price  price  = new Price();
        try {
            if (jsonObjectPricing.has(KEY_NAME_PRICE))
                price.setPrice(ParseUtils.getDouble(jsonObjectPricing.getString(KEY_NAME_PRICE)));
            if (jsonObjectPricing.has(KEY_NAME_ON_SALE))
                price.setOnSale(ParseUtils.getBoolean(jsonObjectPricing.getString(KEY_NAME_ON_SALE)));
            if (jsonObjectPricing.has(KEY_NAME_PROMO_PRICE))
                price.setPromoPrice(ParseUtils.getDouble(jsonObjectPricing.getString(KEY_NAME_PROMO_PRICE)));
            if (jsonObjectPricing.has(KEY_NAME_SAVINGS_TEXT))
                price.setSavingsText(jsonObjectPricing.getString(KEY_NAME_SAVINGS_TEXT));
        }catch (JSONException e){
            Log.e(TAG,e.getMessage());
        }
        return price;
    }
}
