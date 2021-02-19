package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoney() {
        OutputView.printInputMoneyMessage();
        return SCANNER.nextLine();
    }

    public static String inputManualLottoCount() {
        OutputView.printInputManualLottoCount();
        return SCANNER.nextLine();
    }

    public static String inputLottoNumbers() {
        return SCANNER.nextLine();
    }

    public static String inputWinningLottoNumbers() {
        OutputView.printLottoNumbersMessage();
        return SCANNER.nextLine();
    }

    public static String inputBonusNumber() {
        OutputView.printInputBonusBallMessage();

        String input = SCANNER.nextLine();

        OutputView.printNewLineMessage();

        return input;
    }
}