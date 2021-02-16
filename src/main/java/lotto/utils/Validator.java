package lotto.utils;

import lotto.exception.IllegalLottoTicketDuplicateNumber;
import lotto.exception.IllegalLottoTicketNumbers;
import lotto.exception.IllegalLottoTicketSizeError;
import lotto.exception.IllegalMoney;

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

    public static void validateLottoNumbers(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicateNumber(numbers);
        validateLottoNumbersRange(numbers);
    }

    private static void validateDuplicateNumber(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalLottoTicketDuplicateNumber();
        }
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() < LOTTO_TICKET_SIZE) {
            throw new IllegalLottoTicketSizeError();
        }
    }

    private static void validateLottoNumbersRange(List<Integer> numbers) {
        if (isValidNumberRange(numbers)) {
            throw new IllegalLottoTicketNumbers();
        }
    }

    private static boolean isValidNumberRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }

    public static void validateMoneyValue(String input) {
        validateNumberFormat(input);
        validateMinimumMoney(input);
    }

    private static void validateMinimumMoney(String input) {
        int value = Integer.parseInt(input);
        if (value < MINIMUM_MONEY) {
            throw new IllegalMoney();
        }
    }

    private static void validateNumberFormat(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalMoney();
        }
    }
}
