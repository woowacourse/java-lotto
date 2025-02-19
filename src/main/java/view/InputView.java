package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final InputValidator inputValidator = new InputValidator();

    public int getPurchaseAmountInput() {
        String input = getInput();
        return inputValidator.validatePurchaseAmount(input);
    }

    public List<Integer> getWinningNumberInput() {
        String input = getInput();
        return inputValidator.validateWinningNumber(input);
    }

    public int getBonusInput() {
        String input = getInput();
        return inputValidator.validateBonusInput(input);
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
