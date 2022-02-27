package lotto.domain.lotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoConstant;
import lotto.domain.lotto.Lotto;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;
import lotto.exception.InvalidException;

public class LottoWinningNumbers {

    private static final String LOTTO_DELIMITER = ",";
    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private final Lotto winningLotto;
    private int bonusNumber;

    public LottoWinningNumbers(final String numbers, final int bonusNumber) {
//        checkNumbers(numbers);
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

    public int getBonusNumber() {
        return bonusNumber;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
}
