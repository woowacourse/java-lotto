package lotto.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println("구입금액을 입력해주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputMoney();
        }
    }

    public static String inputWinningLottoNumbers() {
        System.out.println("당첨번호를 입력해주세요.");
        return scanner.nextLine()
                .replace(" ", "");
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputBonusNumber();
        }
    }
}
