package lotto.util;

import java.util.List;

import lotto.model.Lotto;

public class InputValidator {

    private static final String LACK_OF_MONEY_ERROR_MESSAGE = "[ERROR] 금액이 적어 로또를 구입할 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "[ERROR] 6개의 숫자가 입력되지 않았습니다.";
    private static final String NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 중복된 숫자가 존재합니다.";
    private static final String RANGE_ERROR_MESSAGE = "[ERROR] 1 ~ 45 사이의 숫자를 입력해주세요.";

    private InputValidator() {
    }

    public static int validatePurchaseAmount(int amount) {
        if (amount < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException(LACK_OF_MONEY_ERROR_MESSAGE);
        }
        return amount;
    }

    public static void validateContain(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public static List<Integer> validateLotto(List<Integer> winningNumbers) {
        validateLength(winningNumbers);
        validateDuplicate(winningNumbers);
        winningNumbers.forEach(InputValidator::validateRange);

        return winningNumbers;
    }

    private static void validateLength(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
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

    public static int validateBonusNumber(int bonusNumber) {
        validateRange(bonusNumber);
        return bonusNumber;
    }
}
