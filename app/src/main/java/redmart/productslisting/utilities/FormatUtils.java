package redmart.productslisting.utilities;

import java.text.DecimalFormat;

/**
 * Created by Ben on 9/2/15.
 */
public class FormatUtils {

    private static DecimalFormat cashDecimalFormat = new DecimalFormat("#0.00");

    public static String  getDollarString (double doubleValue){
        return cashDecimalFormat.format(doubleValue);
    }
}
