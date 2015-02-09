package redmart.productslisting.dao;

import java.util.ArrayList;

import redmart.productslisting.models.Product;

/**
 * Created by Ben on 8/2/15.
 */
public class DummyDataGenerator {


    public static void generateDummyProducts (){
        ArrayList<Product> products =  new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            products.add(product);
        }

        ProductsDAO.setProductsList(products);
    }

}
