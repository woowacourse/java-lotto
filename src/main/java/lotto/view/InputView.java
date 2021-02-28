package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.utils.CustomException;
import lotto.utils.StringChecker;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getAnalogTickets() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String analogCount = scanner.nextLine();
        validate(analogCount);
        List<String> analogTickets = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < Integer.parseInt(analogCount); i++) {
            analogTickets.add(scanner.nextLine());
        }
        return analogTickets;
    }

    private static void validate(String analogCount) {
        validateIsNumber(analogCount);
        validateNotNegative(analogCount);
    }

    private static void validateNotNegative(String analogCount) {
        if (Integer.parseInt(analogCount) < 0) {
            throw new CustomException("발행금액은 음수이면 안 됩니다.");
        }
    }

    private static void validateIsNumber(String analogCount) {
        if (!StringChecker.isNumber(analogCount)) {
            throw new CustomException("수동으로 구매할 로또는 숫자이어야 합니다.");
        }
    }

    public static String getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }

}
