package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String NOT_NUMBER = "입력은 숫자여야 합니다.";
    private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String SPLITTER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static int getMoney() {
        System.out.println(INPUT_MONEY);
        return getNumber();
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS);
        String input = scanner.nextLine();

        return Arrays.stream(input.replaceAll(SPACE, BLANK).split(SPLITTER))
                .peek(InputView::validateNumber)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public static int getBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        return getNumber();
    }

    private static int getNumber() {
        String input = scanner.nextLine();
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NOT_NUMBER);
        }
    }

}
