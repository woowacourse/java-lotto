package lotterymachine.view;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotterymachine.view.ErrorMessage.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NUMBER_DELIMITER = ",";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return toInt(scanner.nextLine());
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getAmount();
        }
    }

    public static List<Integer> getWinningLotteryNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
            return toIntegers(input);
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getWinningLotteryNumbers();
        }
    }

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return toInt(scanner.nextLine());
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getBonusNumber();
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(IS_NOT_NUMBER.getMessage());
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
                .map(String::trim)
                .map(InputView::toInt)
                .collect(Collectors.toList());
    }
}
