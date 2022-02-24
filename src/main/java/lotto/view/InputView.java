package lotto.view;

import java.util.Scanner;
import java.util.stream.Stream;
import lotto.view.utils.IntegerUtils;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    // 인스턴스화 방지
    private InputView() {
    }

    public static int requestMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return IntegerUtils.parse(scanner.nextLine());
    }

    public static int[] requestWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Stream.of(scanner
                .nextLine()
                .split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int requestBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return IntegerUtils.parse(scanner.nextLine());
    }
}
