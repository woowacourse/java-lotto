package view;

import java.util.Scanner;
import model.BonusNumber;
import model.Purchase;
import model.WinningNumber;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public Purchase readPurchaseAmount() {
        // TODO: while 공통 구조 분리
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String purchaseAmountInput = sc.nextLine();
                InputValidator.validateBlank(purchaseAmountInput);
                InputValidator.validateIntegerOverflow(purchaseAmountInput);
                return new Purchase(purchaseAmountInput);
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
                InputValidator.validateBlank(inputWinningNumbers);
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
                InputValidator.validateBlank(bonusNumber);
                InputValidator.validateIntegerOverflow(bonusNumber);
                return new BonusNumber(bonusNumber, winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
