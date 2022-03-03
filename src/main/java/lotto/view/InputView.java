package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NUMBER_DELIMITER = ",";

    private static final String ASK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String ASK_MONEY_MESSAGE = "구입금액을 입력해주세요.";

    private final static Scanner scanner = new Scanner(System.in);
    private static final String ASK_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";

    private InputView() {
    }

    public static int askMoneyInput() {
        System.out.println(ASK_MONEY_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자로 입력하세요.");
        }
    }

    public static List<Integer> askWinningNumbers() {
        System.out.println(ASK_WINNING_NUMBER_MESSAGE);
        try {
            return Arrays.stream(scanner.nextLine().split(NUMBER_DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자로 입력해주세요");
        }
    }

    public static int askBonusNumber() {
        System.out.println(ASK_BONUS_BALL_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자로 입력해주세요");
        }
    }

    public static int askManualCountInput() {
        System.out.println(ASK_MANUAL_COUNT_MESSAGE);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자로 입력해주세요");
        }
    }

    public static List<List<Integer>> askManualNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> collect = collectManualNumber();
            manualNumbers.add(collect);
        }
        return manualNumbers;
    }

    private static List<Integer> collectManualNumber() {
        List<Integer> collect = Arrays.stream(scanner.nextLine().split(NUMBER_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return collect;
    }
}
