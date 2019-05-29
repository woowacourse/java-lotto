package lotto.domain;

public class WinningInformation {
    private final LottoNumbers winningLottoNumbers;
    private final LottoNumber bonusBall;

    public WinningInformation(final LottoNumbers lottoNumbers, final LottoNumber bonusBall) {
        if (lottoNumbers.match(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼과 당첨번호는 겹치면 안됩니다.");
        }
        this.winningLottoNumbers = lottoNumbers;
        this.bonusBall = bonusBall;
    }

    public Rank match(final Lotto lotto) {
        return Rank.valueOf(lotto.match(winningLottoNumbers), lotto.match(bonusBall));
    }
}
