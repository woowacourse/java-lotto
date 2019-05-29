package lotto.view;

import java.util.List;
import java.util.Scanner;

import lotto.utils.InputParser;
import lotto.domain.lotto.Number;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        OutputView.start();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static int getManualCount() {
        OutputView.manualCount();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static List<Number> getWinnerLotto() {
        OutputView.win();
        return InputParser.parseLotto(scanner.nextLine());
    }

    public static int getBonusNumber() {
        OutputView.bonus();
        return InputParser.parseNumber(scanner.nextLine());
    }

    public static List<Number> getManualLotto() {
        return InputParser.parseLotto(scanner.nextLine());
    }
}
