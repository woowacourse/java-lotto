package lotto.domain.lotto;

import lotto.domain.LottoRanking;

public class LottoWinningNumber {

    private final Lotto winningLotto;
    private final Number bonusNumber;

    public LottoWinningNumber(Lotto winninglotto, Number bonusNumber) {
        this.winningLotto = winninglotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRanking getLottoRanking(Lotto lotto) {
        boolean containsBonus = false;
        int count = (int) lotto.getNumbers()
            .stream()
            .filter(number -> winningLotto.contains(number))
            .count();
        if (count == 5 && lotto.contains(bonusNumber)) {
            containsBonus = true;
        }

        return LottoRanking.of(count, containsBonus);
    }
}
