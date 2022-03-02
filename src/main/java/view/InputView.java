package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import util.StringUtil;

public class InputView {

    private static final String QUESTION_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String QUESTION_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String QUESTION_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ", ";
    private static final Scanner scanner = new Scanner(System.in);

    public static String askMoneyInput() {
        System.out.println(QUESTION_MONEY_INPUT);
        return scanner.nextLine();
    }

    public static List<Integer> askWinningNumbers() {
        System.out.println(QUESTION_WINNING_NUMBERS);
        List<String> numbers = Arrays.asList(scanner.nextLine().split(NUMBER_DELIMITER));
        return validateAndCovertNumbersToInteger(numbers);
    }

    public static int askBonusNumber() {
        System.out.println(QUESTION_BONUS_NUMBER);
        return StringUtil.convertToInteger(scanner.nextLine());
    }

    private static List<Integer> validateAndCovertNumbersToInteger(List<String> numbers) {
        return numbers.stream()
                .map(StringUtil::convertToInteger)
                .collect(Collectors.toList());
    }
}
