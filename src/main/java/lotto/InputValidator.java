package lotto;

import lotto.domain.PriceFactory;

public class InputValidator {

    public static boolean isNotValidPrice(String priceInput) {
        return isBlank(priceInput) || isNotInteger(priceInput) || isNotValidPriceRange(priceInput);
    }

    private static boolean isNotValidPriceRange(String price) {
        try {
            PriceFactory.generatePrice(Integer.parseInt(price));
            return false;
        }catch (RuntimeException e){
            return true;
        }
    }

    private static boolean isBlank(String price) {
        if(price.contains(" ")){
            return true;
        }
        return false;
    }

    private static boolean isNotInteger(String price){
        try {
            Integer.parseInt(price);
            return false;
        }catch (NumberFormatException e){
            return true;
        }
    }
}
