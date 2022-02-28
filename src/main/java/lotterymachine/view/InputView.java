package lotterymachine.view;


import lotterymachine.domain.LotteryNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotterymachine.view.ErrorMessage.*;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NUMBER_DELIMITER = ",";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return toInt(SCANNER.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            return getAmount();
        }
    }

    public static List<LotteryNumber> getWinningLotteryNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            return Arrays.stream(SCANNER.nextLine().split(NUMBER_DELIMITER))
                    .map(i -> new LotteryNumber(toInt(i)))
                    .collect(Collectors.toList());
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            return getWinningLotteryNumbers();
        }
    }

    public static LotteryNumber getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return new LotteryNumber(toInt(SCANNER.nextLine()));
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            return getBonusNumber();
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException(IS_NOT_NUMBER.getMessage());
        }
    }
}
