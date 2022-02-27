package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.InvalidException;

public class LottoWinningNumbers {

    private static final String LOTTO_DELIMITER = ",";
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private final Lotto winningLotto;
    private int bonusNumber;

    public LottoWinningNumbers(final String numbers, final int bonusNumber) {
        checkNumbers(numbers);
        this.winningLotto = new Lotto(createWinningLottoNumbers(numbers));
        checkBonusNumber(winningLotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void checkNumbers(String numbers) {
        checkNullAndBlank(numbers);
        checkDelimiterCount(numbers);
        String[] values = numbers.split(LottoWinningNumbers.LOTTO_DELIMITER);
        checkNotInteger(values);
        checkIntegerRange(values);
        checkDuplicateNumber(values);
    }

    private void checkNullAndBlank(String numbers) {
        if (numbers == null || numbers.isBlank()){
            throw new IllegalArgumentException(InvalidException.ERROR_NULL_BLANK);
        }
    }
    private static void checkDelimiterCount(final String numbers) {
        if (numbers.chars()
                .filter(c -> c == LOTTO_DELIMITER.charAt(0))
                .count() != LOTTO_SIZE - 1) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkNotInteger(final String[] values) {
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_NOT_INTEGER);
        }
    }

    private static void checkIntegerRange(final String[] values) {
        if (!Arrays.stream(values)
                .map(Integer::parseInt)
                .allMatch(number -> LOTTO_MIN_RANGE <= number
                        && number <= 45)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateNumber(final String[] values) {
        if (LOTTO_SIZE != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private List<Integer> createWinningLottoNumbers(final String numbers) {
        return Arrays.stream(numbers.split(LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void checkBonusNumber(final Lotto lotto, final int number) {
        checkRangeBonusNumber(number);
        checkDuplicateBonusNumber(lotto, number);
    }

    private static void checkRangeBonusNumber(final int number) {
        if (!(number >= LOTTO_MIN_RANGE
                && number <= LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateBonusNumber(final Lotto lotto, final int number) {
        if (lotto.contains(number)) {
            throw new IllegalArgumentException(InvalidException.ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
