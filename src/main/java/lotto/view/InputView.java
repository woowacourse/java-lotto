package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        return scanner.nextInt();
    }

    public static String inputWinningLottoNumbers() {
        return scanner.nextLine();
    }

    public static int inputBonusNumber() {
        return scanner.nextInt();
    }
}
