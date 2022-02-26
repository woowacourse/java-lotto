package view;

import static util.UserBalanceValidator.validateUserBalance;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestUserMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return validateUserBalance(scanner.nextLine());
    }

    public static String requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
