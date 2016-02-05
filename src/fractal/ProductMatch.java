package fractal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rishadjb on 03/02/16.
 */
public class ProductMatch {

    public static String[] FindMatch(HashMap<String,HashMap> Manufacturers, Listing listing){

        String match[] = {"",""};

        //Iterate through the HashMap of Manufacturers
        for (Map.Entry<String,HashMap> Manufacturer : Manufacturers.entrySet()) {

            match[0] = Manufacturer.getKey();

            //if the listing manufacturer contains the product manufacturer eg: Canon Canada vs Canon
            if(listing.getManufacturer().toLowerCase().contains(match[0])){

                //get the product match for this listing
                match[1] = FindProduct.FindProduct(Manufacturer.getValue(), listing.getTitle());

                return match;

            }
        }

        return match;
    }
}
