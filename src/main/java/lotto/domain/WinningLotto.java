package lotto.domain;

import lotto.exception.LottoCustomException;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;

        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new LottoCustomException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public Rank getRankOfLottoResult(Lotto lotto) {
        int matchCount = matchNumbers(lotto);
        boolean hasBonus = lotto.contains(bonusNumber);
        return Rank.of(matchCount, hasBonus);
    }

    private int matchNumbers(Lotto ticket) {
        return (int) lotto
                .getLottoNumbers()
                .stream()
                .filter(ticket::contains)
                .count();
    }
}
