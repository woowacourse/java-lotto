package view;

import static utils.Messages.*;

import domain.Purchase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.regex.Pattern;
import utils.Separator;

public class InputView {

    private static final String WIN_LOTTO_NUMBER_REGEX = "^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$";
    private static final String DIGIT_REGEX = "^[\\d]+$";
    private static final String LOTTO_NUMBERS_SEPARATE_REGEX = ", ";
    private static final int MONEY_UNIT = 1000;

    private static final Scanner scanner = new Scanner(System.in);


    public static int inputMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        String moneyInput = scanner.nextLine();
        validateMoney(moneyInput);
        return Integer.parseInt(moneyInput);
    }

    private static void validateMoney(String moneyInput) {
        isDigit(moneyInput);
        int money = Integer.parseInt(moneyInput);
        isOverThousand(money);
        isDivideByThousand(money);
    }

    private static void isDigit(String input) {
        if (!Pattern.matches(DIGIT_REGEX, input)) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    private static void isOverThousand(int money) {
        if (money < MONEY_UNIT) {
            throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
        }
    }

    private static void isDivideByThousand(int money) {
        if (money % MONEY_UNIT != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
        }
    }


    public static int inputLottoAmount(int money) {
        System.out.println(LOTTO_MANUAL_MESSAGE);
        String manualCountInput = scanner.nextLine();
        validateLottoAmount(money, manualCountInput);
        return Integer.parseInt(manualCountInput);
    }

    private static void validateLottoAmount(int money, String manualCountInput) {
        isDigit(manualCountInput);
        int manualCount = Integer.parseInt(manualCountInput);
        isRightLottoCount(money, manualCount);
    }

    private static void isRightLottoCount(int money, int manualCount) {
        if (manualCount > money / MONEY_UNIT) {
            throw new IllegalArgumentException(COUNT_OVER_MONEY_ERROR_MESSAGE);
        }
    }

    public static List<List<Integer>> inputManualLottoNumbers(Purchase purchase) {
        System.out.println(LOTTO_MANUAL_INPUT_MESSAGE);
        try {
            return inputMultipleLottoNumbers(purchase.getManualCount());
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return inputManualLottoNumbers(purchase);
        }
    }

    public static List<Integer> inputWinLottoNumbers() {
        System.out.println(LOTTO_NUMBER_INPUT_MESSAGE);
        return inputSingleLottoNumbers();
    }

    private static List<List<Integer>> inputMultipleLottoNumbers(int count) {
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(inputSingleLottoNumbers());
        }
        return lottoNumbers;
    }

    private static List<Integer> inputSingleLottoNumbers() {
        String lottoNumbers = scanner.nextLine();
        validatePattern(lottoNumbers);
        return Separator.splitStringToListInt(lottoNumbers, LOTTO_NUMBERS_SEPARATE_REGEX);
    }

    private static void validatePattern(String lottoNumbers) {
        if (!Pattern.matches(WIN_LOTTO_NUMBER_REGEX, lottoNumbers)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_INPUT_PATTERN_ERROR_MESSAGE);
        }
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
}