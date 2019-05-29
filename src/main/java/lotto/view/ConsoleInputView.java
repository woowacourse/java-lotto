package lotto.view;

import java.util.Scanner;

public class ConsoleInputView {
    public static int inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return new Scanner(System.in).nextInt();
    }

    public static String inputRewardTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Scanner(System.in).nextLine();
    }
}
