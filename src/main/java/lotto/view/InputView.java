package lotto.view;

import static lotto.domain.LottoGame.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_WINNING_DELIMITER = ",";
    private static final String INPUT_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_MANUAL_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private static final String ERROR_NO_INPUT_MESSAGE = "입력이 없습니다.";
    private static final String ERROR_INPUT_EMPTY_MESSAGE = "공백 또는 빈 문자열이 입력하면 안됩니다.";
    private static final String ERROR_NOT_NUMBER_MESSAGE = "숫자 이외의 문자가 입력하면 안됩니다.";
    private static final String ERROR_OVER_INPUT_MONEY_MESSAGE = "구입금액이 부족합니다.";
    private static final String ERROR_NEGATIVE_COUNT_MESSAGE = "투입 금액은 음수이면 안됩니다.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int requestPurchaseMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return nextInt();
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INPUT_EMPTY_MESSAGE);
        }
    }

    private static int toInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_NOT_NUMBER_MESSAGE);
        }
    }

    public static List<Integer> requestWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return nextIntsWithDelimiter();
    }

    public static List<List<Integer>> requestManualNumbers(int count) {
        List<List<Integer>> numbers = new ArrayList<>();
        System.out.println(System.lineSeparator() + INPUT_MANUAL_NUMBERS_MESSAGE);
        for (int i = 0; i < count; i++) {
            numbers.add(nextIntsWithDelimiter());
        }
        return numbers;
    }

    private static List<Integer> nextIntsWithDelimiter() {
        String input = nextLine();

        return Arrays.stream(input.split(INPUT_WINNING_DELIMITER))
                .map(value -> toInt(value.trim()))
                .collect(Collectors.toList());
    }

    public static Integer requestBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        Integer bonusNumber = toInt(nextLine());
        System.out.println();
        return bonusNumber;
    }

    public static int requestManualCount(int inputMoney) {
        System.out.println(System.lineSeparator() + INPUT_MANUAL_COUNT_MESSAGE);
        int inputCount = nextInt();
        validateNegative(inputCount);
        validateOverInputMoney(inputMoney, inputCount);

        return inputCount;
    }

    private static void validateOverInputMoney(int inputMoney, int inputCount) {
        if ((inputMoney / LOTTO_PRICE) < inputCount) {
            throw new IllegalArgumentException(ERROR_OVER_INPUT_MONEY_MESSAGE);
        }
    }

    private static void validateNegative(int inputCount) {
        if (inputCount < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_COUNT_MESSAGE);
        }
    }

    private static String nextLine() {
        String input;
        try {
            input = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(ERROR_NO_INPUT_MESSAGE);
        }
        return input;
    }

    private static int nextInt() {
        String input = nextLine();
        validateEmpty(input);
        return toInt(input);
    }
}
