package view;

public class InputValidator {
    private static final String PURCHASE_AMOUNT_INPUT_ERROR = "숫자로 된 금액을 입력해주세요.\n";
    private static final String WINNING_NUMBER_INPUT_ERROR = ",로 구분한 6개의 숫자를 입력해주세요.\n";

    public void validatePurchaseAmount(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_INPUT_ERROR);
        }
    }

    public void validateWinningNumber(String input) {
        String[] winningNumbers = input.split(",");
        if (winningNumbers.length != 6) {
            throw new IllegalArgumentException(WINNING_NUMBER_INPUT_ERROR);
        }
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
