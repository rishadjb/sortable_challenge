package fractal.sortabletest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * Created by rishadjb on 02/02/16.
 */
public class Listing {

    private String title;
    private String manufacturer;
    private String currency;
    private String price;
    private int CADprice;

    private HashMap<String,Double> currency_rates = new HashMap<String, Double>();

    public Listing CreateListing(String listing_str){

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(listing_str, Listing.class);

    }

    public String getTitle(){
        return title;
    }

    public String getManufacturer(){
        return this.manufacturer;
    }


    public int getPrice(){
        return (int) Double.parseDouble(price);
    }


    //convert price to CAD to help remove deviations later on
    public void setCADprice(){
        CADprice = (int) ( Double.parseDouble(price) * CurrencyConverter.getCADrate(currency));
    }

    public int getCADprice(){
        return CADprice;
    }


}




