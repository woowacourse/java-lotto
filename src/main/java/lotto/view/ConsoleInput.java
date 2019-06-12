package lotto.view;

import lotto.model.LottoRule;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput {
    private final static Scanner SCANNER;
    private final static String INPUT_DELIMITER;
    private static final String MESSAGE_PURCHASE_AMOUNT;

    private final static String ERROR_INPUT_NUMBER;
    private static final String ERROR_LOW_MONEY;
    private final static String EMPTY;

    static {
        SCANNER = new Scanner(System.in);
        MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
        ERROR_INPUT_NUMBER = "올바른 숫자가 입력되지 않았습니다. 다시 입력해 주세요.";
        ERROR_LOW_MONEY = "금액이 모자랍니다.";
        INPUT_DELIMITER = ",\\s*"; // 구분자는 쉼표
        EMPTY = "";
    }

    private final LottoRule rule;

    public ConsoleInput(final LottoRule rule) {
        this.rule = rule;
    }

    private static String inputWithMessage(final String message) {
        if (!message.equals(EMPTY)) {
            System.out.println(message);
        }
        return SCANNER.nextLine().strip();
    }

    private static String[] getInput(final String message) {
        String input = inputWithMessage(message);
        return input.split(INPUT_DELIMITER);
    }

    private static String singleToken(final String message) {
        return getInput(message)[0];
    }

    private static boolean isOnlyNumber(final String string) {
        if (string.length() == 0) {
            return false;
        }
        return string.chars() // String -> IntStream
                .map(i -> (i - '0')) // 형변환 발생
                .noneMatch(i -> (i < 0 || i > 9));
    }

    public static int singleInt(final String message) {
        String rawInput = singleToken(message);
        boolean check = isOnlyNumber(rawInput);
        while (!check) {
            rawInput = singleToken(ERROR_INPUT_NUMBER);
            check = isOnlyNumber(rawInput);
        }
        return Integer.parseInt(rawInput);
    }

    public static List<Integer> tryGetNumbers() {
        Stream<String> rawInput = Arrays.stream(getInput(EMPTY));
        try {
            return rawInput
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_INPUT_NUMBER);
        }
    }

    public int allPurchaseCount() {
        final int result = singleInt(MESSAGE_PURCHASE_AMOUNT) / rule.getPrice();
        if (result < 1) {
            throw new IllegalArgumentException(ERROR_LOW_MONEY);
        }
        return result;
    }


}
