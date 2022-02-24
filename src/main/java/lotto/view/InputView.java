package lotto.view;

import java.util.Scanner;
import lotto.view.utils.IntegerUtils;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int requestMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return IntegerUtils.parse(scanner.nextLine());
    }

    public int requestWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return IntegerUtils.parse(scanner.nextLine());
    }

    public int requestBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return IntegerUtils.parse(scanner.nextLine());
    }
}
