package view.input;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final String INPUT_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;
    private final InputParser inputParser;

    public ConsoleInputView(final InputParser inputParser) {
        this.scanner = new Scanner(System.in);
        this.inputParser = inputParser;
    }

    @Override
    public int readPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_MESSAGE);
        final String input = readInput();
        return inputParser.parsePurchaseAmount(input);
    }

    @Override
    public List<Integer> readWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        final String input = readInput();
        return inputParser.parseWinningNumbers(input);
    }

    @Override
    public int readBonusBall() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        final String input = readInput();
        return inputParser.parseBonusBall(input);
    }

    private String readInput() {
        return scanner.nextLine();
    }
}
