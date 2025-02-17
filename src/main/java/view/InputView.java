package view;

import java.util.Scanner;
import util.Validator;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public String readPurchaseAmount() {
        String purchaseAmountInput = sc.nextLine();
        Validator.validateBlank(purchaseAmountInput);
        Validator.validateIntegerOverflow(purchaseAmountInput);
        return purchaseAmountInput;
    }

    public String readWinningNumbers() {
        String inputWinningNumbers = sc.nextLine();
        Validator.validateBlank(inputWinningNumbers);
        return inputWinningNumbers;
    }

    public String readBonusNumbers() {
        String bonusNumber = sc.nextLine();
        Validator.validateBlank(bonusNumber);
        Validator.validateIntegerOverflow(bonusNumber);
        return bonusNumber;
    }
}
