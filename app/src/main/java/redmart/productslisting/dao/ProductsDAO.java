package redmart.productslisting.dao;

import java.util.ArrayList;

import redmart.productslisting.models.Product;


public class ProductsDAO {

    private static ArrayList<Product> productsList = new ArrayList<>();

    /**
     * @return list of Products
     */
    public static ArrayList<Product> getProductsList() {
        return productsList;
    }

    /**
     * @param productsList the list of products
     */
    public static void setProductsList(ArrayList<Product> productsList) {
        ProductsDAO.productsList = productsList;
    }

    public static void appendToList(ArrayList<Product> products) {
        if (products != null)
            productsList.addAll(products);
    }


    public static Product get(int position){
        return productsList.get(position);
    }

    public static int count(){
        return  productsList.size();
    }

}
