package lotto.util;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.Lotto;

public class InputValidator {

    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해주세요.";
    private static final String LACK_OF_MONEY_ERROR_MESSAGE = "[ERROR] 금액이 적어 로또를 구입할 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "[ERROR] 6개의 숫자가 입력되지 않았습니다.";
    private static final String NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 중복된 숫자가 존재합니다.";
    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요.";

    public static int validatePurchaseAmount(String amount) {
        int validatedAmount = validateNumber(amount);
        validateAvailablePurchase(validatedAmount);
        return validatedAmount;
    }

    private static int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private static void validateAvailablePurchase(int amount) {
        if (amount < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException(LACK_OF_MONEY_ERROR_MESSAGE);
        }
    }

    public static void validateContain(List<String> winningNumbers, String bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public static List<Integer> validateLotto(List<String> winningNumbers) {
        validateLength(winningNumbers);
        validateDuplicate(winningNumbers);
        winningNumbers.forEach(winningNumber -> validateRange(validateNumber(winningNumber)));

        return winningNumbers.stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
    }

    private static void validateLength(List<String> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(List<String> numbers) {
        long count = numbers.stream()
            .distinct()
            .count();

        if (numbers.size() != count) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private static void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(RANGE_ERROR_MESSAGE);
        }
    }

    public static int validateBonusNumber(String bonusNumber) {
        int validatedNumber = validateNumber(bonusNumber);
        validateRange(validatedNumber);
        return Integer.parseInt(bonusNumber);
    }
}
