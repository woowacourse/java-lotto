package lotto.view;

import lotto.messageConstants.MessageConstants;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final int MONEY_UNIT = 1000;


    public int inputMoney() {
        System.out.println(MessageConstants.INPUT_MONEY);
        String money = SCAN.nextLine();

        while (validate(money)) {
            System.err.println(MessageConstants.ERROR_INPUT);
            money = SCAN.nextLine();
        }

        return Integer.parseInt(money);
    }

    private boolean validate(String money) {
        return validInputNumber(money) || validMoneyUnit(money);
    }

    private boolean validMoneyUnit(String input) {
        return Integer.parseInt(input) % MONEY_UNIT != 0;
    }

    private boolean validInputNumber(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        return !matcher.find();
    }
}
