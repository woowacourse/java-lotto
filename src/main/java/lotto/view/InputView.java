package lotto.view;

import java.util.Scanner;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class InputView {
    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String EX_USER_PRICE_FORMAT_MESSAGE = "1000 이상의 숫자만 입력해주세요.";
    private static final int MIN_USER_PRICE_RANGE = 1000;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static double generateInvalidUserPrice() {
        try {
            System.out.println(INPUT_PRICE);
            double price = Double.parseDouble(inputByUser());
            checkUserPrice(price);
            return price;
        } catch (Exception e) {
            System.out.println(EX_USER_PRICE_FORMAT_MESSAGE);
            return generateInvalidUserPrice();
        }
    }

    private static void checkUserPrice(double price) {
        if (price < MIN_USER_PRICE_RANGE) {
            throw new IllegalArgumentException();
        }
    }

    private static String inputByUser() {
        return SCANNER.nextLine().trim();
    }
}
