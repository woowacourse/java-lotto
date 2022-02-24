package lotto.receiver.validator;

import java.util.HashSet;
import java.util.List;
import lotto.exception.LottoException;

public class LottoValidator {

    private static final int LOTTO_SIZE = 6;

    public static void validate(List<String> numbers) {
        checkSize(numbers);
        checkDuplicate(numbers);
    }

    private static void checkSize(List<String> numbers) {
        if (!isCorrectSize(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<String> numbers) {
        return numbers.size() == LOTTO_SIZE;
    }

    private static void checkDuplicate(List<String> numbers) {
        if (isDuplicate(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplicate(List<String> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }
}
