package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해주세요";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private static final String NUMBER_REGEX = "\\d+";

    private static final Scanner input = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return validateMoney(input.nextLine().trim());
    }

    private static int validateMoney(String money) {
        if (!Pattern.matches(NUMBER_REGEX, money)) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
        return Integer.parseInt(money);
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return validateLottoNumbers(input.nextLine().trim());
    }

    private static List<Integer> validateLottoNumbers(String numbers) {
        String[] splitNumbers = numbers.split(",");
        return Arrays.stream(splitNumbers)
                .map(String::trim)
                .map(InputView::validateNumber)
                .collect(Collectors.toList());
    }

    private static int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return toIntNumber(input.nextLine().trim());
    }

    private static int toIntNumber(String number) {
        return validateNumber(number.trim());
    }

    public static int inputManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return toIntNumber(input.nextLine().trim());
    }

    public static List<List<Integer>> inputManualNumbers(int count) {
        List<List<Integer>> manualLottos = new ArrayList<>();
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        for (int i = 0; i < count; i++) {
            manualLottos.add(validateLottoNumbers(input.nextLine().trim()));
        }
        return manualLottos;
    }
}
