package lotto.view.input;

import lotto.utils.InputUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

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

    public static List<Integer> inputWinningLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String text = scanner.next();
            return InputUtils.parseIntegerList(text);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputWinningLotto();
        }
    }
}
