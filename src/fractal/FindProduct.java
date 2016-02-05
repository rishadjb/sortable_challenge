package fractal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rishadjb on 02/02/16.
 */
public class FindProduct {

    public static String FindProduct(HashMap<String, Product> ProductMap, String listingTitle){

        String matched_key = "";
        String matched_product_model = "";

        listingTitle = listingTitle.toLowerCase();

        for (Map.Entry<String,Product> entry : ProductMap.entrySet()) {

            String product_key = entry.getKey();

            //set to lowercase; remove characters
            String modded_listing = ModifyString(listingTitle).toLowerCase();

            String product_model = entry.getValue().getModel().toLowerCase();

            //set to lowercase; remove characters
            String product_family = ModifyString(entry.getValue().getFamily()).toLowerCase();

/*
            System.out.println("product_key:" + product_key);
            System.out.println("modded_listing:" + modded_listing);
            System.out.println("product_model:" + product_model);
            System.out.println("product_family:" + product_family);
            System.out.println();
*/



            //Checks:
            //1 - if the listing title or the modded listing title contains the product model
            //2 - if the listing title contains the product family
            //3 - if this product model is longer than previously matched model
            //if( (modded_listing.contains(product_model) || modded_listing.contains(entry.getValue().getModel())) && modded_listing.contains(product_family) && compareKeys(product_key, matched_key) ){
            if( ( listingTitle.contains(product_model) || modded_listing.contains(product_model) )
                    && modded_listing.contains(product_family)
                    && compareKeys(product_model, matched_product_model) ){

                matched_product_model = product_model;

                matched_key = product_key;

            }
        }

        return matched_key;


    }

    //method to remove certain characters
    private static String ModifyString(String input){

        input = input.replace("_","");
        input = input.replace("-","");
        input = input.replace(" ","");

        return input;
    }

    //checks if the new model has a better match than the previously matched model
    private static boolean compareKeys(String model_new, String model_old){
        return model_new.length() > model_old.length();
    }

}
