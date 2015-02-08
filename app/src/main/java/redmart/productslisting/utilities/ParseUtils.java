package redmart.productslisting.utilities;

public class ParseUtils
{
    public static Double getDouble(String paramString)
    {
        try
        {
            Double localDouble = Double.valueOf(paramString);
            return localDouble;
        }
        catch (Exception localException)
        {
        }
        return Double.valueOf(0.0D);
    }

    public static Integer getInteger(String paramString)
    {
        try
        {
            Integer localInteger = Integer.valueOf(paramString.split("\\.")[0]);
            return localInteger;
        }
        catch (Exception localException)
        {
        }
        return Integer.valueOf(0);
    }

    public static Long getLong(String paramString)
    {
        try
        {
            Long localLong = Long.valueOf(paramString);
            return localLong;
        }
        catch (Exception localException)
        {
        }
        return Long.valueOf(0L);
    }


    public static Boolean getBoolean(String  paramString){
        if (paramString ==  null) return false;
        if (paramString.equals("1")) return  true;
        if (paramString.equalsIgnoreCase("true")) return true;
        if (paramString.equalsIgnoreCase("y")) return true;
        if (paramString.equalsIgnoreCase("yes")) return  true;
        return false;
    }
}