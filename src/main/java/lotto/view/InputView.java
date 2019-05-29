package lotto.view;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class InputView {
    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String EX_USER_PRICE_FORMAT_MESSAGE = "1000 이상의 숫자만 입력해주세요.";
    private static final String EX_MANUAL_COUNT_RANGE_MESSAGE = "구입 금액을 초과하지 않는 숫자를 입력해주세요(로또는 한장에 1000원입니다.)";
    private static final int MIN_USER_PRICE_RANGE = 1000;
    private static final int ONE_LOTTO_PRICE = MIN_USER_PRICE_RANGE;

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

    public static double generateInvalidManualCount(double userPrice) {
        try {
            System.out.println(INPUT_MANUAL);
            double manualCount = Double.parseDouble(inputByUser());
            checkManualCount(manualCount, userPrice);
            return manualCount;
        } catch (Exception e) {
            System.out.println(EX_MANUAL_COUNT_RANGE_MESSAGE);
            return generateInvalidManualCount(userPrice);
        }
    }

    private static void checkManualCount(double manualCount, double userPrice) {
        if (manualCount * ONE_LOTTO_PRICE > userPrice) {
            throw new IllegalArgumentException();
        }
    }

}
