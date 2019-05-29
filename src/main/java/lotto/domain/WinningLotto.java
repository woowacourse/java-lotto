package lotto.domain;

public class WinningLotto {
    private Lotto winningLottoNo;
    private LottoNo bonusNo;

    private WinningLotto(Lotto winningLottoNo, LottoNo bonusNo) {
        validateLottoNotContainBonusNo(winningLottoNo, bonusNo);
        this.winningLottoNo = winningLottoNo;
        this.bonusNo = bonusNo;
    }

    public static WinningLotto of(Lotto winningLottoNo, LottoNo bonusNo) {
        return new WinningLotto(winningLottoNo, bonusNo);
    }

    private void validateLottoNotContainBonusNo(Lotto winningLottoNo, LottoNo bonusNo) {
        if (winningLottoNo.matchNo(bonusNo)) {
            throw new InvalidNumberException("당첨 번호는 보너스 볼의 번호와 달라야 합니다.");
        }
    }

    int findCountOfMatchNo(Lotto targetLotto) {
        return targetLotto.findCountOfMatchNo(winningLottoNo);
    }

    boolean checkBonusNoIn(Lotto targetLotto) {
        return targetLotto.matchNo(bonusNo);
    }
}