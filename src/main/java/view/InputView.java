package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private final Scanner sc;

    public InputView(Scanner scanner
    ) {
        this.sc = scanner;
    }

    public int askPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
        String purchaseAmount = sc.nextLine();

        return InputConverter.convertPurchaseAmount(purchaseAmount);
    }

    public List<Integer> askWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = sc.nextLine();

        return InputConverter.convertWinningNumbers(winningNumbers);
    }

    public int askBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = sc.nextLine();

        return InputConverter.convertBonusNumber(bonusNumber);
    }
}
