package lotto.view;

import lotto.exception.ConvertFailException;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String CONVERT_FAIL_EXCEPTION_MESSAGE = "%s : 잘못된 숫자 입력";
    private static final String EMPTY_EXCEPTION_MESSAGE = "입력값이 비어있습니다.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputBettingMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String bettingMoney = scanner.nextLine();
        validateEmpty(bettingMoney);
        return convertToInteger(bettingMoney);
    }


    public String inputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String winningNumber = scanner.nextLine();
        validateEmpty(winningNumber);
        return winningNumber;
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String bonusNumber = scanner.nextLine();
        validateEmpty(bonusNumber);
        return convertToInteger(bonusNumber);
    }

    private void validateEmpty(String input) {
        if (isEmpty(input)) {
            throw new IllegalArgumentException(EMPTY_EXCEPTION_MESSAGE);
        }
    }

    private boolean isEmpty(String input) {
        return input == null || (input.trim()).isEmpty();
    }

    private int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ConvertFailException(String.format(CONVERT_FAIL_EXCEPTION_MESSAGE, input));
        }
    }
}
