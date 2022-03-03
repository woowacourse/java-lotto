package lotto.view;

import java.util.Scanner;
import lotto.validator.CountOfManualLottoValidator;
import lotto.validator.MoneyValidator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static long inputMoney() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            System.out.println(INPUT_MONEY_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputMoney(input);
        }
        return Long.parseLong(input);
    }

    private static boolean validateInputMoney(String input) {
        try {
            MoneyValidator.validate(Long.parseLong(input));
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    public static int inputCountOfManualLotto(long money) {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            OutputView.printNewLine();
            System.out.println(INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputCountOfManualLotto(input, money);
        }
        return Integer.parseInt(input);
    }

    private static boolean validateInputCountOfManualLotto(String input, long money) {
        try {
            CountOfManualLottoValidator.validate(Integer.parseInt(input),money);
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    public static String inputLottoNumbers() {
        return scanner.nextLine();
    }

    public static String inputWinningLotto() {
        OutputView.printNewLine();
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}
