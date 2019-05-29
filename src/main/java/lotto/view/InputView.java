package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputLottoMoney() {
        System.out.println("금액을 입력");
        return scanner.nextLine();
    }

    public static String inputWinningLotto() {
        System.out.println("당첨번호");
        return scanner.nextLine();
    }
}
