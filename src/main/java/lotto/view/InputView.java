package lotto.view;

import java.util.Scanner;
import lotto.domain.Lotto;
import lotto.validator.CountOfManualLottoValidator;
import lotto.validator.MoneyValidator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static long inputMoney() {
        try {
            System.out.println(INPUT_MONEY_MESSAGE);
            long money = Long.parseLong(scanner.nextLine());
            MoneyValidator.validate(money);
            return money;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    public static int inputCountOfManualLotto(long money) {
        try {
            OutputView.printNewLine();
            System.out.println(INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE);
            int countForBuy = Integer.parseInt(scanner.nextLine());
            CountOfManualLottoValidator.validate(countForBuy,money);
            return countForBuy;
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return inputCountOfManualLotto(money);
        }
    }

    public static void printInputLottoNumbersMessage() {
        OutputView.printNewLine();
        System.out.println(INPUT_LOTTO_NUMBERS_MESSAGE);
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
