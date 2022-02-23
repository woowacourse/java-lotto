package domain;

import java.util.List;

public class LottoReferee {
    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public LottoReferee(List<Integer> winningNumbers, Integer bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult getLottoResult(Lotto lotto) {
        int matchCount = getMatchCount(lotto);
        boolean hasBonus = containsBonus(lotto);

        return LottoResult.of(matchCount, hasBonus);
    }

    private int getMatchCount(Lotto lotto) {
        return (int) lotto.getNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean containsBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
