package lotto.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.LottoNumber;
import lotto.domain.Money;

public class Validation {

    public static final Character LOTTO_DELIMITER = ',';
    public static final String ERROR_CREATE_LOTTO = "[ERROR] 잘못된 숫자 입력입니다.";
    public static final String ERROR_INTEGER_RANGE = "[ERROR] 1~45 사이의 수가 아닙니다.";
    public static final String ERROR_NOT_INTEGER = "[ERROR] 양의 정수가 아닙니다.";
    public static final String ERROR_WRONG_INPUT_MONEY =
            "[ERROR] 올바른 구매 값을 입력해주세요. 로또가격: " + Money.BASIC_LOTTO_MONEY + "원";
    public static final String ERROR_DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 숫자 입력입니다.";

    public static void checkDuplicateNumber(final List<Integer> numbers) {
        if (LottoNumber.LOTTO_SIZE != Set.copyOf(numbers).size()) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    public static void checkInputLottoWinningNumbers(final String numbers) {
        checkDelimiterCount(numbers);
        checkCreateLottoWinningNumbers(numbers);
        checkNotInteger(numbers);
        checkIntegerRange(numbers);
        checkDuplicateNumber(numbers);
    }

    private static void checkDelimiterCount(final String numbers) {
        if (numbers.chars()
                .filter(c -> c == LOTTO_DELIMITER)
                .count() != LottoNumber.LOTTO_SIZE - 1) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    private static void checkCreateLottoWinningNumbers(final String numbers) {
        try {
            numbers.split(LottoWinningNumbers.LOTTO_DELIMITER);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    private static void checkNotInteger(final String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_DELIMITER);
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private static void checkIntegerRange(final String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_DELIMITER);
        if (!Arrays.stream(values)
                .map(Integer::parseInt)
                .allMatch(number -> LottoNumber.LOTTO_MIN_RANGE <= number
                        && number <= 45)) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    public static void checkDuplicateNumber(final String numbers) {
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_DELIMITER);
        if (LottoNumber.LOTTO_SIZE != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    public static void checkInputMoney(final String money) {
        checkValidateInt(money);
        checkDivideMoney(Integer.parseInt(money));
    }

    public static void checkValidateInt(final String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    public static void checkDivideMoney(final int money) {
        if (!(money >= Money.BASIC_LOTTO_MONEY && money % Money.BASIC_LOTTO_MONEY == 0)) {
            throw new IllegalArgumentException(ERROR_WRONG_INPUT_MONEY);
        }
    }

    public static void checkBonusNumber(final Lotto lotto, final int number) {
        checkRangeBonusNumber(number);
        checkDuplicateBonusNumber(lotto, number);
    }

    private static void checkRangeBonusNumber(final int number) {
        if (!(number >= LottoNumber.LOTTO_MIN_RANGE
                && number <= LottoNumber.LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateBonusNumber(final Lotto lotto, final int number) {
        if (lotto.contains(number)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }
}
