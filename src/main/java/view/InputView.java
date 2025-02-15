package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner sc;
    private final InputConverter inputConverter;

    public InputView(Scanner scanner) {
        this.sc = scanner;
        this.inputConverter = new InputConverter();
    }

    public int askPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = sc.nextLine();

        return inputConverter.convertPurchaseAmount(purchaseAmount);
    }

    public List<Integer> askWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = sc.nextLine();

        return inputConverter.convertWinningNumbers(winningNumbers);
    }

    public int askBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = sc.nextLine();

        return inputConverter.convertBonusNumber(bonusNumber);
    }
}
