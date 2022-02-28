package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import utils.Validator;

public class InputView {

    private static final String INPUT_NUMBER_DELIMITER = ",";
    private static final int INPUT_NUMBER_SPLIT_OPTION = -1;
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String getInput() {
        return SCANNER.nextLine();
    }

    public static int getMoney() {
        OutputView.printMoneyInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return Integer.parseInt(input);
    }

    public static List<String> getLastWinLotto() {
        OutputView.printWinLottoInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        return Arrays.stream(input.split(INPUT_NUMBER_DELIMITER, INPUT_NUMBER_SPLIT_OPTION))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static int getBonusNumber() {
        OutputView.printBonusInstruction();
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return Integer.parseInt(input);
    }
}
