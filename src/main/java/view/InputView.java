package view;

import java.util.Scanner;
import model.BonusNumber;
import model.WinningNumber;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public int readPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String purchaseAmountInput = sc.nextLine();
                int purchaseAmount = Integer.parseInt(purchaseAmountInput);
                if (purchaseAmount < 1000 || purchaseAmount > 100000 || purchaseAmount % 1000 != 0) {
                    throw new IllegalArgumentException("1000 이상 100000 이하의 1000으로 나누어 떨어지는 정수를 입력해주세요.");
                }
                return purchaseAmount;
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException("구입 금액은 정수로 입력해주세요.");
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public WinningNumber readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while(true) {
            try {
                String inputWinningNumbers = sc.nextLine();
                return new WinningNumber(inputWinningNumbers);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public BonusNumber readBonusNumbers(WinningNumber winningNumber) {
        System.out.println("보너스 볼을 입력해 주세요.");
        while (true) {
            try {
                String bonusNumber = sc.nextLine();
                return new BonusNumber(bonusNumber, winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
