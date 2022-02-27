package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int BONUS_CONFIRMATION_CRITERIA = 5;

    private final Set<Number> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        lottoNumbers = new HashSet<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new Number(number));
        }
    }

    public Rank calculateRank(WinningNumbers winningNumber) {
        int matched = matchedRegularNumbers(winningNumber);
        boolean hasBonus = false;
        if (matched == BONUS_CONFIRMATION_CRITERIA) {
            hasBonus = hasMatchedNumber(winningNumber.getBonus());
        }
        return Rank.getWinnerPrizeByMatched(matched, hasBonus);
    }

    private int matchedRegularNumbers(WinningNumbers winningNumber) {
        return (int) winningNumber.getWinningNumbers().stream()
                .filter(lottoNumbers::contains).count();
    }

    private boolean hasMatchedNumber(Number number) {
        return lottoNumbers.contains(number);
    }

    public Set<Number> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

}
