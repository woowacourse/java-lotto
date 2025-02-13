package view.input;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private final Scanner scanner;
    private final InputParser inputParser;

    public ConsoleInputView(final InputParser inputParser) {
        this.scanner = new Scanner(System.in);
        this.inputParser = inputParser;
    }

    @Override
    public int readPurchaseAmount() {
        final String input = scanner.nextLine();
        return inputParser.parsePurchaseAmount(input);
    }

    @Override
    public List<Integer> readWinningNumber() {
        final String input = scanner.nextLine();
        return inputParser.parseWinningNumbers(input);
    }

    @Override
    public int readBonusBall() {
        final String input = scanner.nextLine();
        return inputParser.parseBonusBall(input);
    }
}
