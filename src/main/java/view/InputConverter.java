package view;

import static domain.Lotto.LOTTO_PRICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {

    private static final Pattern WINNING_NUMBER_PATTERN = Pattern.compile("^[0-9]+(,[0-9]+)*$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public List<Integer> convertWinningNumbers(String input) {
        Matcher matcher = WINNING_NUMBER_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("당첨 번호 입력 양식이 올바르지 않습니다.");
        }

        List<Integer> winningNumbers = Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .toList();

        validateUniqueNumber(winningNumbers);

        return winningNumbers;
    }

    public int convertBonusNumber(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("보너스 번호 입력 양식이 올바르지 않습니다.");
        }

        return Integer.parseInt(input);
    }

    public int convertPurchaseAmount(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("구입 금액 입력 양식이 올바르지 않습니다.");
        }

        int purchaseAmount = Integer.parseInt(input);
        validatePurchaseAmountRange(purchaseAmount);
        validatePurchaseAmountIsDividedByUnit(purchaseAmount);

        return purchaseAmount;
    }

    private void validatePurchaseAmountIsDividedByUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은" + LOTTO_PRICE + "원 단위로 가능합니다.");
        }
    }

    private void validatePurchaseAmountRange(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 " + LOTTO_PRICE + "원 이상부터 가능합니다.");
        }
    }

    private void validateUniqueNumber(List<Integer> values) {
        values.forEach(value -> {
            if (Collections.frequency(values, value) > 1) {
                throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
            }
        });
    }
}
