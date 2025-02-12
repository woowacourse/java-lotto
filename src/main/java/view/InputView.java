package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";

    public String askPurchaseAmount() {
        Scanner sc = new Scanner(System.in);

        System.out.println(PURCHASE_AMOUNT_PROMPT);
        String purchaseAmount = sc.nextLine();
        sc.close();

        return purchaseAmount;
    }

    public List<Integer> askWinningNumbers() {
        Scanner sc = new Scanner(System.in);

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = sc.nextLine();
        sc.close();

        return InputConverter.convertWinningNumbers(winningNumbers);
    }

    public int askBonusNumber() {
        Scanner sc = new Scanner(System.in);

        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumber = sc.nextLine();
        sc.close();

        return InputConverter.convertBonusNumber(bonusNumber);
    }
}
