package lotto.view.input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            scanner.nextLine();
            return inputMoney();
        }
    }
}
