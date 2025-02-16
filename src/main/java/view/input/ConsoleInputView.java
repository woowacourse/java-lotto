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
        System.out.println("구입금액을 입력해 주세요.");
        final String input = scanner.nextLine();
        return inputParser.parsePurchaseAmount(input);
    }

    @Override
    public List<Integer> readWinningNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        final String input = scanner.nextLine();
        return inputParser.parseWinningNumbers(input);
    }

    @Override
    public int readBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        final String input = scanner.nextLine();
        return inputParser.parseBonusBall(input);
    }
}
