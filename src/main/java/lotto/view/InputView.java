package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputMoney() {
        OutputView.printInputMoneyMessage();
        return SCANNER.nextLine();
    }

    public static String lottoNumber() {
        OutputView.printLottoNumbersMessage();
        return SCANNER.nextLine();
    }

    public static String bonusNumber() {
        OutputView.printInputBonusBallMessage();

        String input = SCANNER.nextLine();

        OutputView.printNewLineMessage();

        return input;
    }
}