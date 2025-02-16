package view;

import java.util.Arrays;
import java.util.List;

public class InputValidator {
    private static final String WINNING_NUMBERS_INPUT_ERROR_MESSAGE = "당첨 번호는 중복되지 않는 1 이상 45 이하의 정수여야합니다.\n";
    private static final String PURCHASE_AMOUNT_INPUT_ERROR = "숫자로 된 금액을 입력해주세요.\n";
    private static final String WINNING_NUMBER_INPUT_ERROR = ",로 구분한 6개의 숫자를 입력해주세요.\n";
    public static final String BONUS_INPUT_ERROR = "보너스 번호는 숫자를 입력해주세요.\n";

    public int validatePurchaseAmount(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_INPUT_ERROR);
        }

        return Integer.parseInt(input);
    }

    public List<Integer> validateWinningNumber(String input) {
        String[] winningNumbers = input.split(",");
        if (winningNumbers.length != 6) {
            throw new IllegalArgumentException(WINNING_NUMBER_INPUT_ERROR);
        }

        return Arrays.stream(winningNumbers)
                .map(this::getNumber)
                .toList();
    }

    public int validateBonusInput(String input) {
        if(!isNumber(input)) {
            throw new IllegalArgumentException(BONUS_INPUT_ERROR);
        }

        return Integer.parseInt(input);
    }

    private int getNumber(String rawNumber) {
        rawNumber = rawNumber.trim();
        if (!isNumber(rawNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_INPUT_ERROR_MESSAGE);
        }
        return Integer.parseInt(rawNumber);
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
