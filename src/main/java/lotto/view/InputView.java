package lotto.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputMoney();
        }
    }

    public static String inputWinningLottoNumbers() {
        return scanner.nextLine()
                .trim();
    }

    public static int inputBonusNumber() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputBonusNumber();
        }
    }
}
