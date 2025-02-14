package view.input;

import constans.OutputMessage;
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
        System.out.println(OutputMessage.INPUT_PURCHASE_AMOUNT);
        final String input = scanner.nextLine();

        return inputParser.parsePurchaseAmount(input);
    }

    @Override
    public List<Integer> readWinningNumber() {
        System.out.println(OutputMessage.INPUT_WINNING_NUMBERS);
        final String input = scanner.nextLine();

        return inputParser.parseWinningNumbers(input);
    }

    @Override
    public int readBonusBall() {
        System.out.println(OutputMessage.INPUT_BONUS_NUMBER);
        final String input = scanner.nextLine();
        
        return inputParser.parseBonusBall(input);
    }
}
