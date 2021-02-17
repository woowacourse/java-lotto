package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.exception.*;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 49;
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    public static final int MINIMUM_MONEY = 1000;

    private Validator() {

    }

    public static void validateLottoNumbers(List<LottoNumber> numbers) {
        if (isInvalidateLottoSize(numbers) || isDuplicateNumber(numbers)) {
            throw new IllegalLottoNumbers();
        }
    }

    public static void validateMoneyValue(String input) {
        if (isInvalidNumberFormat(input) || isLessThanMinimumMoney(input)) {
            throw new IllegalMoney();
        }
    }

    public static void validateLottoNumber(String input) {
        if (isInvalidNumberFormat(input) || isInvalidLottoNumberRange(input)) {
            throw new IllegalLottoNumber();
        };
    }

    private static boolean isDuplicateNumber(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    private static boolean isInvalidateLottoSize(List<LottoNumber> numbers) {
        return numbers.size() != LOTTO_TICKET_SIZE;
    }

    private static boolean isInvalidLottoNumberRange(String input) {
        return Integer.parseInt(input) < MIN_NUMBER || Integer.parseInt(input) > MAX_NUMBER;
    }

    private static boolean isLessThanMinimumMoney(String input) {
        return Integer.parseInt(input) < MINIMUM_MONEY;
    }

    private static boolean isInvalidNumberFormat(String input) {
        return !NUMBER_PATTERN.matcher(input).matches();
    }
}
