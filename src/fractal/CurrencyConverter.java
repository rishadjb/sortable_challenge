package fractal;


/**
 * Created by rishadjb on 04/02/16.
 */
public class CurrencyConverter {

    public static double getCADrate(String currency){

        switch (currency) {
            case "USD": return 1.38;

            case "GBP": return 2.01;

            case "EUR": return 1.53;

            default: return 1.00;
        }

    }
}
