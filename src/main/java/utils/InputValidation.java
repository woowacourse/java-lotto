package utils;

public class InputValidation {

    private static final String ERROR_NON_INTEGER = "가격은 정수만 가능합니다.";

    public static int validatePrice(String inputPrice) {
        int price = checkNonInteger(inputPrice);

        return price;
    }

    private static int checkNonInteger(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NON_INTEGER);
        }
    }
}
