package lotto.utils;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.IllegalLottoNumberException;
import lotto.exception.IllegalLottoNumbersException;
import lotto.exception.IllegalMoneyException;
import lotto.exception.IllegalWinningLottoException;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    public static final int MINIMUM_MONEY = 1000;
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 49;

    private Validator() {

    }

    public static void validateMoneyValue(String input) {
        if (isInvalidNumberFormat(input) || isLessThanMinimumMoney(input)) {
            throw new IllegalMoneyException();
        }
    }

    private static boolean isInvalidNumberFormat(String input) {
        return !NUMBER_PATTERN.matcher(input).matches();
    }

    private static boolean isLessThanMinimumMoney(String input) {
        return Integer.parseInt(input) < MINIMUM_MONEY;
    }

    public static void validateLottoNumber(String input) {
        if (isInvalidNumberFormat(input) || isInvalidLottoNumberRange(input)) {
            throw new IllegalLottoNumberException();
        }
    }

    private static boolean isInvalidLottoNumberRange(String input) {
        return Integer.parseInt(input) < MIN_NUMBER || Integer.parseInt(input) > MAX_NUMBER;
    }

    public static void validateLottoNumbers(List<LottoNumber> numbers) {
        if (isInvalidateLottoSize(numbers) || isDuplicateNumber(numbers)) {
            throw new IllegalLottoNumbersException();
        }
    }

    private static boolean isInvalidateLottoSize(List<LottoNumber> numbers) {
        return numbers.size() != LOTTO_TICKET_SIZE;
    }

    private static boolean isDuplicateNumber(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    public static void validateWinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.isContainLottoNumber(bonusNumber)) {
            throw new IllegalWinningLottoException();
        }
    }
}
