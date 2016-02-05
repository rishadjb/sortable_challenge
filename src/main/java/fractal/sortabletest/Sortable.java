package fractal.sortabletest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by rishadjb on 02/02/16.
 */
public class Sortable {

    public static void main(String[] args){

        //A HashMap of Manufacturers that contains HashMaps of Products
        HashMap<String, HashMap> Manufacturers = new HashMap<String,HashMap> ()  ;

        //File paths
        File file_products = new File("input/" + "products.txt");
        File file_listings = new File("input/" + "listings.txt");

        //File Readers
        FileReader FR_Products;
        FileReader FR_Listings;

        /*
        =========================================================================================
        - Read from products file
        - Create new products
        - Create a manufacturer hashmap if it doesn't exist already
        - Insert each product into it's respective manufacturer Hashmap
        =========================================================================================
        */

        try{
            FR_Products = new FileReader(file_products);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }

        try {
            BufferedReader br = new BufferedReader(FR_Products);

            String line;

            while( (line = br.readLine()) != null && !line.isEmpty()){

                //Create a new product
                Product new_product = new Product().CreateProduct(line);
                
                //Create a new HashMap for the manufacturer if it doesn't exist already
                if(!Manufacturers.containsKey(new_product.getManufacturer().toLowerCase())){
                    Manufacturers.put( new_product.getManufacturer().toLowerCase() , new HashMap<String, Product>() );
                }

                //Add this product to its manufacturer's HashMap
                //key is in lowercase to ease the matching of listings
                Manufacturers.get( new_product.getManufacturer().toLowerCase() ) . put(new_product.getName(), new_product);
            }

            FR_Products.close();

        } catch (IOException e1){
            e1.printStackTrace();
            return;
        }
        //=========================================================================================


        /*
        =========================================================================================
        - Read from listings file
        - Find the matching product if one exists
        - If a match is found, get the matching product from the hashmap
        - Insert listing into the arraylist of the product
        =========================================================================================
        */
        try{
            FR_Listings = new FileReader(file_listings);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }

        try {
            BufferedReader br = new BufferedReader(FR_Listings);

            String line;

            while( (line = br.readLine()) != null && !line.isEmpty()){

                //Create a new Listing
                Listing new_listing = new Listing().CreateListing(line);

                //get the manufacturer and product name
                String[] match = ProductMatch.FindMatch(Manufacturers, new_listing);

                //if product name isn't blank i.e. no matching product found
                if(!match[1].equals("")){

                    //get the matching product
                    //match[0] - key for manufacturer
                    //match[1] - key for product
                    Product matched_product = (Product) Manufacturers . get(match[0]) . get(match[1]);

                    //add listing to the product's arraylist
                    matched_product.addListing(new_listing);

                }

            }

            FR_Products.close();

        } catch (IOException e1){
            e1.printStackTrace();
            return;
        }
        //=========================================================================================

        //Create the output file
        new CreateJSON(Manufacturers);



    }

}
