package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getInputMoney() {
        OutputView.printInputMoneyMessage();
        return SCANNER.nextLine();
    }

    public static String getLottoNumbers() {
        OutputView.printLottoNumbersMessage();
        return SCANNER.nextLine();
    }

    public static String getBonusBallNumber() {
        OutputView.printInputBonusBallMessage();
        String value = SCANNER.nextLine();
        OutputView.printNewLineMessage();
        return value;
    }
}