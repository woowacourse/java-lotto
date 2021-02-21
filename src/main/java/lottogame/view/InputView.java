package lottogame.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = scanner.nextLine();
        validateInteger(money);
        return Integer.parseInt(money);
    }

    private static void validateInteger(String money) {
        if (!money.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("구입 금액은 정수로만 입력 가능합니다.");
        }
    }

    public static String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
