package lotto.view;

import java.util.Scanner;

public class ConsoleInputView {
    public static int inputMoney() {
        return new Scanner(System.in).nextInt();
    }

    public static int inputRewardTicket() {
        return new Scanner(System.in).nextInt();
    }
}
