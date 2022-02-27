package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String REQUEST_MESSAGE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String ERROR_MESSAGE_TYPE_OF_MONEY = "금액은 숫자가 아닐 수 없습니다.";
    private static final String ERROR_MESSAGE_RANGE_OF_MONEY = "금액은 0이하일 수 없습니다.";
    private static final String REQUEST_MESSAGE_WINNING_LOTTO_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_MESSAGE_INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String REGEX_OF_LOTTO_NUMBER = ", ";
    private static final int REGEX_LIMIT = -1;
    private static final int MINIMUM_VALUE = 0;

    private static final Scanner scanner = new Scanner(System.in);


    private static String getInput() {
        return scanner.nextLine();
    }

    public static int getPurchaseMoney() {
        System.out.println(REQUEST_MESSAGE_INPUT_PURCHASE_MONEY);
        String inputMoney = getInput();
        validatePurchaseMoney(inputMoney);

        return Integer.parseInt(inputMoney);
    }

    private static void validatePurchaseMoney(String value) {
        validateNumber(value);
        validateRange(value);
    }

    private static void validateNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TYPE_OF_MONEY);
        }
    }

    private static void validateRange(String value) {
        if (Integer.parseInt(value) <= MINIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RANGE_OF_MONEY);
        }
    }

    public static List<Integer> getWinningLottoNumbers() {
        System.out.println(REQUEST_MESSAGE_WINNING_LOTTO_NUMBERS);
        String inputWinningLottoNumbers = getInput();

        List<String> winningLottoNumbers = convertStringList(inputWinningLottoNumbers);
        validateWinningLottoNumbers(winningLottoNumbers);

        return convertIntegerList(winningLottoNumbers);
    }

    private static List<String> convertStringList(String inputWinningLottoNumbers) {
        return Arrays.stream(inputWinningLottoNumbers.split(REGEX_OF_LOTTO_NUMBER, REGEX_LIMIT))
            .collect(Collectors.toList());
    }

    private static void validateWinningLottoNumbers(List<String> values) {
        for (String value : values) {
            validateNumber(value);
        }
    }

    private static List<Integer> convertIntegerList(List<String> values) {
        return values.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int getBonusBall() {
        System.out.println(REQUEST_MESSAGE_INPUT_BONUS_BALL);
        String inputBonusBall = getInput();
        validateBonusBall(inputBonusBall);

        return Integer.parseInt(inputBonusBall);
    }

    private static void validateBonusBall(String value) {
        validateNumber(value);
        validateRange(value);
    }
}
