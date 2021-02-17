package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static String takeMoneyInput(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요");
        String money = scanner.nextLine();

        return money;
    }


    public static List<Integer> winningNumbers() {
        return Arrays.asList();
    }

    public static int bonusNumber() {
        return 0;
    }
}
