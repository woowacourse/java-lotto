package lotto.viewer;

import java.util.Scanner;

public class InputView {
    final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int purchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }
}
