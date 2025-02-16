package converter;

import java.util.Arrays;
import java.util.List;

public class InputConverter {

    private static final String WINNING_NUMBER_DELIMITER = ",";

    public List<Integer> convertStringToWinningNumberValue(String rawInput) {
        validateWinningNumber(rawInput);
        return Arrays.stream(rawInput.split(WINNING_NUMBER_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .mapToInt(n -> n).boxed()
                .toList();
    }

    public int convertStringToMoneyValue(String rawInput) {
        validateInputMoney(rawInput);
        return Integer.parseInt(rawInput);
    }

    public int convertStringToBonusNumberValue(String rawInput) {
        validateNotStringNumber(rawInput);
        return Integer.parseInt(rawInput);
    }

    private void validateInputMoney(String money) {
        validateNotStringNumber(money);
    }

    private void validateWinningNumber(String rawWinningNumbers) {
        String[] values = rawWinningNumbers.split(WINNING_NUMBER_DELIMITER);
        for (String value : values) {
            validateNotStringNumber(value);
        }
    }

    private void validateNotStringNumber(String value) {
        try {
            Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않는 입력입니다.", e);
        }
    }
}
