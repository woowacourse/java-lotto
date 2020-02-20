package lotto.view;

import java.util.List;
import java.util.Scanner;

import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        return scanner.nextLine();
    }

    public static List<Integer> inputLastWeekWinningNumbers() {
        System.out.println("지난주 당첨번호을 입력해주세요");
        String input = scanner.nextLine();
        return StringUtils.parseWithStandard(input);
    }

    public static int inputBonusBall() {
        System.out.println("보너스볼을 입력해주세요");
        return scanner.nextInt();
    }
}
