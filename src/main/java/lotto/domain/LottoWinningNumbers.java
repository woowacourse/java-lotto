package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.InvalidException;

public class LottoWinningNumbers {

    public static final String LOTTO_DELIMITER = ",";
    private final Lotto winningLotto;
    private int bonusNumber;
    private HashMap<Rank, Integer> winningResult;

    public LottoWinningNumbers(final String numbers, final int bonusNumber) {
        initWinningResult();
        this.winningLotto = new Lotto(createWinningLottoNumbers(numbers));
        checkBonusNumber(winningLotto, bonusNumber);
        this.bonusNumber = bonusNumber;
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
        if (!(number >= LottoNumber.LOTTO_MIN_RANGE
                && number <= LottoNumber.LOTTO_MAX_RANGE)) {
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

    public void initWinningResult() {
        winningResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winningResult.put(rank, 0);
        }
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
