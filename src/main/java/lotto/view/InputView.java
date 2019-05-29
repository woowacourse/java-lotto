package lotto.view;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static final String EX_LOTTO_FORMAT_LANGE = "1~45 사이의 숫자를 입력해주세요";

    public static long generateInvalidUserPrice() {
        try {
            System.out.println(INPUT_PRICE);
            long price = Long.parseLong(inputByUser());
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

    public static long generateInvalidManualCount(double userPrice) {
        try {
            System.out.println(INPUT_MANUAL);
            long manualCount = Long.parseLong(inputByUser());
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

    public static Lotto generateInvalidLotto() {
        try {
            List<Integer> lottoNumbers = generateLottoNumbers(inputByUser());
            return Lotto.generate(lottoNumbers);
        } catch (NumberFormatException e) {
            System.out.println(EX_LOTTO_FORMAT_LANGE);
            return generateInvalidLotto();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateInvalidLotto();
        }
    }

    private static List<Integer> generateLottoNumbers(String inputByUser) {
        List<Integer> lottoNumbers = new ArrayList<>();
        List<String> lottos = Arrays.asList(inputByUser.replaceAll(" ","").split(","));
        for (String lotto : lottos) {
            lottoNumbers.add(Integer.parseInt(lotto));
        }
        return lottoNumbers;
    }

}
