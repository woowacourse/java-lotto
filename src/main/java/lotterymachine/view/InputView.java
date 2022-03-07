package lotterymachine.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NUMBER_DELIMITER = ",";
    private static final String IS_NOT_NUMBER = "숫자만 입력할 수 있습니다.";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return toInt(SCANNER.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            return getAmount();
        }
    }

    public static int getManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return toInt(SCANNER.nextLine());
    }

    public static List<Integer> getWinningLotteryNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            return Arrays.stream(SCANNER.nextLine().split(NUMBER_DELIMITER))
                    .map(i -> toInt(i.trim()))
                    .collect(Collectors.toList());
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getWinningLotteryNumbers();
        }
    }

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int number = toInt(SCANNER.nextLine());
            printEmptyLine();
            return number;
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            return getBonusNumber();
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException(IS_NOT_NUMBER);
        }
    }

    public static List<List<Integer>> getManualLotteryNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> value = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            value.add(Arrays.stream(SCANNER.nextLine().split(","))
                    .map(input -> toInt(input.trim()))
                    .collect(Collectors.toList()));
        }
        return value;
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}