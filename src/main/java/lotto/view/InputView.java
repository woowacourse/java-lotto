package lotto.view;

import lotto.exception.ConvertFailException;

import java.util.Scanner;

public class InputView {
    private final static Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static int inputBettingMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String bettingMoney = scanner.nextLine();
        validate(bettingMoney);
        return convertToInteger(bettingMoney);
    }


    public static String inputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String winningNumber = scanner.nextLine();
        validate(winningNumber);
        return winningNumber;
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String bonusNumber = scanner.nextLine();
        validate(bonusNumber);
        return convertToInteger(bonusNumber);
    }

    private static void validate(String input) {
        if (isEmpty(input)) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }

    private static boolean isEmpty(String input) {
        return input == null || (input.trim()).isEmpty();
    }

    private static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ConvertFailException("잘못된 숫자 입력");
        }
    }
}
