package redmart.productslisting.dao;

import java.util.ArrayList;

import redmart.productslisting.models.Product;


public class ProductsDAO {

    private static ArrayList<Product> productsList  = new ArrayList<>();

    /**
     *
     * @return list of Products
     */
    public static ArrayList<Product> getProductsList() {
        return productsList;
    }

    /**
     *
     * @param productsList the list of products
     */
    public static void setProductsList(ArrayList<Product> productsList) {
        ProductsDAO.productsList = productsList;
    }




}
