package view;

import static utils.Messages.*;

import java.util.Scanner;

import java.util.regex.Pattern;

public class InputView {

    private static final String WIN_LOTTO_NUMBER_REGEX = "^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$";
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        try {
            String money = scanner.nextLine();
            return Integer.parseInt(money);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    public static int inputLottoAmount() {
        System.out.println(LOTTO_MANUAL_MESSAGE);
        try {
            String amount = scanner.nextLine();
            return Integer.parseInt(amount);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    public static String inputManualLottoNumbers() {
        System.out.println(LOTTO_MANUAL_INPUT_MESSAGE);
        try {
            return getLottoNumbers();
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return inputManualLottoNumbers();
        }
    }

    public static String inputWinLottoNumbers() {
        System.out.println(LOTTO_NUMBER_INPUT_MESSAGE);
        return getLottoNumbers();
    }

    private static String getLottoNumbers() {
        String lottoNumbers = scanner.nextLine();
        validatePattern(lottoNumbers);
        return lottoNumbers;
    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_INPUT_MESSAGE);
        try {
            String bonus = scanner.nextLine();
            return Integer.parseInt(bonus);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    private static void validatePattern(String lottoNumbers) {
        if (!Pattern.matches(WIN_LOTTO_NUMBER_REGEX, lottoNumbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE);
        }
    }
}