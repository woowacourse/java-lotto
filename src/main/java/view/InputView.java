package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String REQUEST_MESSAGE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String REQUEST_MESSAGE_INPUT_MANUAL_COUNT = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_MESSAGE_INPUT_MANUAL_LOTTOS_NUMBERS = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String ERROR_MESSAGE_TYPE_OF_NUMBER = "잘못된 숫자가 입력되었습니다.";
    private static final String ERROR_MESSAGE_RANGE_OF_MONEY = "금액은 0이하일 수 없습니다.";
    private static final String ERROR_MESSAGE_RANGE_OF_MANUAL_COUNT = "구매할 수동 로또 수량은 음수일 수 없습니다.";
    private static final String REQUEST_MESSAGE_WINNING_LOTTO_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_MESSAGE_INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String REGEX_OF_LOTTO_NUMBER = ",";
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
            throw new IllegalArgumentException(ERROR_MESSAGE_TYPE_OF_NUMBER);
        }
    }

    private static void validateRange(String value) {
        if (Integer.parseInt(value) <= MINIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RANGE_OF_MONEY);
        }
    }

    public static int getPurchaseManualCount() {
        System.out.println(REQUEST_MESSAGE_INPUT_MANUAL_COUNT);
        String inputManualCount = getInput();
        validatePurchaseManualCount(inputManualCount);

        return Integer.parseInt(inputManualCount);
    }

    private static void validatePurchaseManualCount(String inputManualCount) {
        validateNumber(inputManualCount);
        validateRangeManualCount(inputManualCount);
    }

    private static void validateRangeManualCount(String inputManualCount) {
        if (Integer.parseInt(inputManualCount) < MINIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_RANGE_OF_MANUAL_COUNT);
        }
    }

    public static List<List<Integer>> getManualLottoNumbers(int manualCount) {
        System.out.println(REQUEST_MESSAGE_INPUT_MANUAL_LOTTOS_NUMBERS);

        List<List<Integer>> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            List<String> manualLottoNumber = convertStringList(getInput());
            validateLottoNumbers(manualLottoNumber);

            manualLottos.add(convertIntegerList(manualLottoNumber));
        }

        return manualLottos;
    }

    public static List<Integer> getWinningLottoNumbers() {
        System.out.println(REQUEST_MESSAGE_WINNING_LOTTO_NUMBERS);
        String inputWinningLottoNumbers = getInput();

        List<String> winningLottoNumbers = convertStringList(inputWinningLottoNumbers);
        validateLottoNumbers(winningLottoNumbers);

        return convertIntegerList(winningLottoNumbers);
    }

    private static List<String> convertStringList(String inputWinningLottoNumbers) {
        return Arrays.stream(inputWinningLottoNumbers.split(REGEX_OF_LOTTO_NUMBER, REGEX_LIMIT))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    private static void validateLottoNumbers(List<String> values) {
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
