package lotto.domain;

public class WinningLotto {
    private Lotto winningLotto;
    private LottoNo bonusNo;

    WinningLotto(Lotto winningLotto, LottoNo bonusNo) {
        if (winningLotto.contains(bonusNo)) {
            throw new IllegalArgumentException("보너스 번호가 로또 번호에 포함됩니다.");
        }
        this.winningLotto = winningLotto;
        this.bonusNo = bonusNo;
    }

    Rank calRank(LottoTicket lotto) {
        return Rank.valueOf(lotto.includedNumber(winningLotto), lotto.contains(bonusNo));
    }
}