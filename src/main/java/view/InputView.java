package view;

import java.util.Scanner;

public class InputView {

    private final InputValidator inputValidator = new InputValidator();

    public String getPurchaseAmountInput() {
        String input = getInput();
        inputValidator.validatePurchaseAmount(input);

        return input;
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        return input;
    }
}
