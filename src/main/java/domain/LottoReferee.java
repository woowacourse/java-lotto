package domain;

import java.util.List;

public class LottoReferee {
    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoReferee(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult getLottoResult(Lotto lotto) {
        int matchCount = getMatchCount(lotto);
        boolean hasBonus = containsBonus(lotto);

        return LottoResult.of(matchCount, hasBonus);
    }

    private int getMatchCount(Lotto lotto) {
        return (int) lotto.getChosenNumbers()
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean containsBonus(Lotto lotto) {
        return lotto.getChosenNumbers().contains(bonusNumber);
    }
}
