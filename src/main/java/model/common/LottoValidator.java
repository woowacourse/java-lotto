package model.common;

import exception.ExceptionMessage;
import java.util.HashSet;
import java.util.List;

public class LottoValidator {

    public static void validateLottoNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }

    public static void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_LOTTO_SIZE);
        }
    }

    public static void validateLottoNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }
}
