package lotto.domain.lotto;

import lotto.domain.LottoRanking;

public class WinningLotto {

    private final Lotto winningLotto;
    private final Number bonusNumber;

    public WinningLotto(Lotto winninglotto, Number bonusNumber) {
        validateDuplicateBonusNumber(winninglotto, bonusNumber);
        this.winningLotto = winninglotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRanking getLottoRanking(Lotto lotto) {
        boolean containsBonus = false;
        int count = (int)lotto.getNumbers()
            .stream()
            .filter(number -> winningLotto.contains(number))
            .count();
        if (count == 5 && lotto.contains(bonusNumber)) {
            containsBonus = true;
        }

        return LottoRanking.of(count, containsBonus);
    }

    private void validateDuplicateBonusNumber(Lotto winninglotto, Number bonusNumber) {
        if (winninglotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 중복되면 안됩니다.");
        }
    }
}
