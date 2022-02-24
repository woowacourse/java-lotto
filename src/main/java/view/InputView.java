package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String QUESTION_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String QUESTION_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String QUESTION_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static String askMoneyInput() {
        System.out.println(QUESTION_MONEY_INPUT);
        return scanner.nextLine();
    }

    public static List<Integer> askWinningNumbers() {
        System.out.println(QUESTION_WINNING_NUMBERS);
        String[] numbers = scanner.nextLine().split(", ");

        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : numbers) {
            winningNumbers.add(Integer.parseInt(number));
        }
        return winningNumbers;
    }

    public static int askBonusNumber() {
        System.out.println(QUESTION_BONUS_NUMBER);
        return Integer.parseInt(scanner.nextLine());
    }
}
