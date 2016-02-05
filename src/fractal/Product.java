package fractal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by rishadjb on 02/02/16.
 */
public class Product {

    private String product_name;
    private String manufacturer;
    private String model;
    private String family;
    private String date;

    //maintains an average price for the product so large deviations can be removed later on.
    private int ave_price=0;

    //arraylist to contain the listings for the product
    ArrayList<Listing> listings= new ArrayList ();


    public Product CreateProduct(String product_str){

        //Use gson to create product from the json string
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(product_str, Product.class);

    }


    public String getName(){
        return this.product_name;
    }

    public String getManufacturer(){
        return this.manufacturer;
    }

    public String getModel(){
        return this.model;
    }

    public String getFamily(){

        if(family == null){
            return model;
        }
        return this.family;
    }

    public String getDate(){
        return this.date;
    }

    //updates the average price when a new listing is inserted
    private void updateAvePrice(int new_price){
        ave_price = ( (ave_price * (listings.size()-1) ) + new_price) / listings.size();
    }

    public int getAvePrice(){ return ave_price; }

    //insert a new listing into the listing arraylist
    public void addListing(Listing listing){
        listings.add(listing);

        //set the CAD price for the listing
        listing.setCADprice();

        //update the average price in CAD for the product
        updateAvePrice(listing.getCADprice());

    }


    public ArrayList<Listing> getListingArray(){
        return listings;
    }


}




