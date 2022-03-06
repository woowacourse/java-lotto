package view;

import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static util.InputUtil.splitAndChangeToInt;
import static util.InputUtil.checkAndChangeToInt;

public class InputView {

    private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_NUM_OF_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";
    private static final String ERROR_MONEY_NON_INTEGER = "가격은 정수만 가능합니다.";
    private static final String ERROR_COUNT_NON_INTEGER = "구매 개수는 정수만 가능합니다.";

    private final static Scanner scanner = new Scanner(System.in);

    public interface IndividualInput<T> {
        T get();
    }

    public static <T> T commonInputProcess(IndividualInput<T> individualInputs) {
        try {
            return individualInputs.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return commonInputProcess(individualInputs);
        }
    }

    public static int inputMoney() {
        System.out.println(lineSeparator() + INPUT_MONEY);
        return checkAndChangeToInt(scanner.nextLine(), ERROR_MONEY_NON_INTEGER);
    }

    public static int[] inputWinningLottoNumbers() {
        System.out.println(lineSeparator() + INPUT_WINNING_LOTTO_NUMBERS);
        return splitAndChangeToInt(scanner.nextLine(), ERROR_BALL_NON_INTEGER);
    }

    public static int inputBonus() {
        System.out.println(INPUT_BONUS_NUMBER);
        return checkAndChangeToInt(scanner.nextLine(), ERROR_BALL_NON_INTEGER);
    }

    public static int inputNumOfManualLotto() {
        System.out.println(lineSeparator() + INPUT_NUM_OF_MANUAL_LOTTO);
        return checkAndChangeToInt(scanner.nextLine(), ERROR_COUNT_NON_INTEGER);
    }

    public static int[][] inputManualLottoNumbers(final int numOfManualLotto) {
        System.out.println(lineSeparator() + INPUT_MANUAL_LOTTO_NUMBERS);
        int[][] inputLottoNumbers = new int[numOfManualLotto][6];
        for (int i = 0; i < numOfManualLotto; i++) {
            inputLottoNumbers[i] = splitAndChangeToInt(scanner.nextLine(), ERROR_BALL_NON_INTEGER);
        }
        return inputLottoNumbers;
    }
}
