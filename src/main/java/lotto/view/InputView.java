package lotto.view;

import lotto.domain.Number;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final Pattern WINNER_NUMBER_PATTERN = Pattern.compile("(([0-9]){1,},){5}([0-9])");
    private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String INPUT_BUY_LOTTO = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String ERROR_INPUT = "잘못된 입력 입니다. 다시 입력해주세요.";
    private static final String INITIALIZATION = "";

    public static int inputMoney() {
        System.out.println(INPUT_MONEY);
        String money = INITIALIZATION;

        money = getCheckString(money);

        return Integer.parseInt(money);
    }

    public static String inputWinnerNumber() {
        System.out.println(INPUT_BUY_LOTTO);
        String winnerNumber = INITIALIZATION;

        try {
            winnerNumber = SCAN.nextLine();
            validNumbers(winnerNumber);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return winnerNumber;
    }

    public static Number inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL);
        String bonusBall = INITIALIZATION;

        bonusBall = getCheckString(bonusBall);

        return new Number(Integer.parseInt(bonusBall));
    }

    public static int inputHandNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String number = INITIALIZATION;

        number = getCheckString(number);

        return Integer.parseInt(number);
    }

    public static String[] inputHandleNumber(int round) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> handNumbers = new ArrayList<>();
        String[] numbers = new String[round];

        try {
            for (int i = 0; i < round; i++) {
                numbers[i] = SCAN.nextLine();
            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            for (int i = 0; i < round; i++) {
                numbers[i] = SCAN.nextLine();
            }
        }

        return numbers;
    }

    private static String getCheckString(String input) {
        try {
            input = SCAN.nextLine();
            validInputNumber(input);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            input = SCAN.nextLine();
        }
        return input;
    }

    private static void validInputNumber(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_INPUT);
        }
    }

    private static void validNumbers(String input) {
        Matcher matcher = WINNER_NUMBER_PATTERN.matcher(input);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_INPUT);
        }
    }
}
