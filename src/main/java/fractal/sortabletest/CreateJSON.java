package fractal.sortabletest;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Created by rishadjb on 03/02/16.
 */
public class CreateJSON {

    CreateJSON(HashMap<String, HashMap> Manufacturers) {

        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output/" + "output.txt"), "utf-8"));

            for (HashMap<String, Product> ProductMap : Manufacturers.values()) {

                for (Product product : ProductMap.values()) {


                    int ave_price = product.getAvePrice();

                    //Remove outliers i.e. price deviation of greater than 75%

                    ListIterator<Listing> iter = product.getListingArray().listIterator();
                    while(iter.hasNext()){

                        Listing ls = iter.next();



                        if ( ( (double)Math.abs(ave_price - ls.getCADprice()) / ave_price ) > 0.75){

                            iter.remove();
                        }
                    }



                    String json = new Gson().toJson(product);

                    writer.write(json + "\n");

                    /*
                    writer.write(product.getName() + "\n");
                    writer.write(product.getAvePrice() + "\n");

                    for (Listing listing : product.getListingArray()){
                        writer.write(listing.getPrice() + "\t\t");
                        writer.write(listing.getCADprice() + "\t\t");
                        writer.write(listing.getTitle() + "\n");
                        //writer.write(json + "\n");
                    }
                    */

                    //writer.write("\n");


                }

            }

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }




    }


}
