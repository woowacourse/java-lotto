package lotto.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String ERROR_INPUT = "잘못된 입력 입니다. 다시 입력해주세요.";

    public static int inputMoney() {
        System.out.println(INPUT_MONEY);
        String money = "";

        try {
            money = SCAN.nextLine();
            validInputNumber(money);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return Integer.parseInt(money);
    }

    private static void validInputNumber(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_INPUT);
        }
    }
}
