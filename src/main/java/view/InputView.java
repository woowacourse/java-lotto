package view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public String readPurchaseAmount() {
        String purchaseAmountInput = sc.nextLine();
        InputValidator.validateBlank(purchaseAmountInput);
        InputValidator.validateIntegerOverflow(purchaseAmountInput);
        return purchaseAmountInput;
    }

    public String readWinningNumbers() {
        String inputWinningNumbers = sc.nextLine();
        InputValidator.validateBlank(inputWinningNumbers);
        return inputWinningNumbers;
    }

    public String readBonusNumbers() {
        String bonusNumber = sc.nextLine();
        InputValidator.validateBlank(bonusNumber);
        InputValidator.validateIntegerOverflow(bonusNumber);
        return bonusNumber;
    }
}
