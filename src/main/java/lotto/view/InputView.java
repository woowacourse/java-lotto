package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String DELIMITER = ",";
    public static final String BLANK = " ";
    public static final String REPLACEMENT = "";

    private InputView() {
    }

    public static String inputMoney() {
        OutputView.printInputMoneyMessage();
        return SCANNER.nextLine();
    }

    public static List<String> inputWinningNumbers() {
        OutputView.printInputWinningNumbers();
        return Arrays.asList(SCANNER.nextLine()
                .replace(BLANK, REPLACEMENT)
                .split(DELIMITER));
    }

    public static String inputBonusNumber() {
        OutputView.printInputBonusNumberMessage();
        return SCANNER.nextLine();
    }
}
