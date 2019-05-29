package lotto.domain;

public class WinningInformation {
    private final LottoNumbers winningLottoNumbers;

    public WinningInformation(final LottoNumbers lottoNumbers) {
        this.winningLottoNumbers = lottoNumbers;
    }

    public Rank match(final Lotto lotto) {
        return Rank.valueOf(lotto.match(winningLottoNumbers));
    }
}
