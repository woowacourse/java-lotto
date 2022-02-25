package view;

import java.util.Scanner;
import utils.Validator;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String getInput() {
        return SCANNER.nextLine();
    }

    public static String getMoney() {
        OutputView.printMoneyInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return input;
    }

    public static String getWinLotto() {
        OutputView.printWinLottoInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        return input;
    }

    public static String getBonusNumber() {
        OutputView.printBonusInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return input;
    }
}
