package view.input;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private final Scanner scanner;
    private final InputParser inputParser;
    private final InputValidator inputValidator;

    public ConsoleInputView(final InputParser inputParser, final InputValidator inputValidator) {
        this.scanner = new Scanner(System.in);
        this.inputParser = inputParser;
        this.inputValidator = inputValidator;
    }

    @Override
    public int readPurchaseAmount() {
        String input = scanner.nextLine();
        int parsePurchaseAmount = inputParser.parsePurchaseAmount(input);
        inputValidator.validatePurchaseAmount(parsePurchaseAmount);
        return parsePurchaseAmount;
    }

    @Override
    public List<Integer> readWinningNumber() {
        String input = scanner.nextLine();
        inputValidator.validateWinningNumbersText(input);
        List<Integer> winningNumbers = inputParser.parseWinningNumbers(input);
        inputValidator.validateWinningNumbers(winningNumbers);
        return winningNumbers;
    }

    @Override
    public int readBonusNumber() {
        return 0;
    }
}
