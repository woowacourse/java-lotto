package lotto.view;

import java.util.Scanner;

public class InputView {

    public static String inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
