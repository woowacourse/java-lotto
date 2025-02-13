package view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public String readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmountInput = sc.nextLine();
        InputValidator.validateBlank(purchaseAmountInput);
        InputValidator.validateIntegerOverflow(purchaseAmountInput);
        return purchaseAmountInput;
    }

    public String readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = sc.nextLine();
        InputValidator.validateBlank(inputWinningNumbers);
        return inputWinningNumbers;
    }

    public String readBonusNumbers() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = sc.nextLine();
        InputValidator.validateBlank(bonusNumber);
        InputValidator.validateIntegerOverflow(bonusNumber);
        return bonusNumber;
    }
}
