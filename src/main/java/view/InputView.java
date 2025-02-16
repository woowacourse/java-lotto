package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public String readPurchaseAmount() {
        String purchaseAmountInput = sc.nextLine();
        InputValidator.validateBlank(purchaseAmountInput);
        InputValidator.validateIntegerOverflow(purchaseAmountInput);
        return purchaseAmountInput;
    }

    public List<String> readWinningNumbers() {
        String inputWinningNumbers = sc.nextLine();
        InputValidator.validateBlank(inputWinningNumbers);
        return List.of(inputWinningNumbers.split(DELIMITER));
    }

    public String readBonusNumbers() {
        String bonusNumber = sc.nextLine();
        InputValidator.validateBlank(bonusNumber);
        InputValidator.validateIntegerOverflow(bonusNumber);
        return bonusNumber;
    }
}
