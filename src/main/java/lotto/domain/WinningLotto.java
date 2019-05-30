package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private LottoNo bonusNo;

    private WinningLotto(List<LottoNo> lottoNo, LottoNo bonusNo) {
        super(lottoNo, LottoType.WINNING);
        validateLottoNotContainBonusNo(lottoNo, bonusNo);
        this.bonusNo = bonusNo;
    }

    public static WinningLotto of(List<LottoNo> winningLottoNo, LottoNo bonusNo) {
        return new WinningLotto(winningLottoNo, bonusNo);
    }

    private void validateLottoNotContainBonusNo(List<LottoNo> winningLottoNo, LottoNo bonusNo) {
        if (winningLottoNo.contains(bonusNo)) {
            throw new InvalidNumberException("당첨 번호는 보너스 볼의 번호와 달라야 합니다.");
        }
    }

    int findCountOfMatchNo(Lotto targetLotto) {
        return targetLotto.findCountOfMatchNo(lottoNo);
    }

    boolean checkBonusNoIn(Lotto targetLotto) {
        return targetLotto.matchNo(bonusNo);
    }
}