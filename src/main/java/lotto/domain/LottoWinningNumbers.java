package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.InvalidException;

public class LottoWinningNumbers {

    private final Lotto winningLotto;
    private int bonusNumber;
    private HashMap<Rank, Integer> winningResult;

    public LottoWinningNumbers(final String numbers, final int bonusNumber) {
        initWinningResult();
        checkInputLottoWinningNumbers(numbers);
        this.winningLotto = new Lotto(createWinningLottoNumbers(numbers));
        checkBonusNumber(winningLotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void initWinningResult() {
        winningResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winningResult.put(rank, 0);
        }
    }

    private static void checkInputLottoWinningNumbers(final String numbers) {
        checkNullAndEmpty(numbers);
        checkDelimiterCount(numbers);
        checkCreateLottoWinningNumbers(numbers);
        checkNotInteger(numbers);
        checkIntegerRange(numbers);
        checkDuplicateNumber(numbers);
    }

    private static void checkNullAndEmpty(final String numbers) {
        if (numbers == null || numbers.isBlank()) {
            throw new IllegalArgumentException(InvalidException.ERROR_NULL_BLANK);
        }
    }

    private static void checkDelimiterCount(final String numbers) {
        if (numbers.chars()
                .filter(c -> c == Constant.LOTTO_DELIMITER.charAt(0))
                .count() != Constant.LOTTO_SIZE - 1) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkCreateLottoWinningNumbers(final String numbers) {
        try {
            numbers.split(Constant.LOTTO_DELIMITER);
        } catch (java.lang.Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkNotInteger(final String numbers) {
        String[] values = numbers.split(Constant.LOTTO_DELIMITER);
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (java.lang.Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_NOT_INTEGER);
        }
    }

    private static void checkIntegerRange(final String numbers) {
        String[] values = numbers.split(Constant.LOTTO_DELIMITER);
        if (!Arrays.stream(values)
                .map(Integer::parseInt)
                .allMatch(number -> Constant.LOTTO_MIN_RANGE <= number
                        && number <= Constant.LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateNumber(final String numbers) {
        String[] values = numbers.split(Constant.LOTTO_DELIMITER);
        if (Constant.LOTTO_SIZE != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private List<Integer> createWinningLottoNumbers(final String numbers) {
        return Arrays.stream(numbers.split(Constant.LOTTO_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void checkBonusNumber(final Lotto lotto, final int number) {
        checkRangeBonusNumber(number);
        checkDuplicateBonusNumber(lotto, number);
    }

    private static void checkRangeBonusNumber(final int number) {
        if (!(number >= Constant.LOTTO_MIN_RANGE
                && number <= Constant.LOTTO_MAX_RANGE)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateBonusNumber(final Lotto lotto, final int number) {
        if (lotto.contains(number)) {
            throw new IllegalArgumentException(InvalidException.ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public void calculateWinning(final Lotto lotto) {
        int matchCount = (int) winningLotto.getNumbers()
                .stream()
                .filter(number -> lotto.getNumbers().contains(number))
                .count();
        boolean hasBonusNumber = lotto.getNumbers().contains(bonusNumber);
        Rank rank = Rank.matchRank(matchCount, hasBonusNumber);

        winningResult.put(rank, winningResult.get(rank) + 1);
    }

    public int calculateWinningMoney() {
        return Arrays.stream(Rank.values())
                .mapToInt(rank -> winningResult.get(rank) * rank.getMoney())
                .sum();
    }

    public int getRankCount(final Rank rank) {
        return winningResult.get(rank);
    }
}
