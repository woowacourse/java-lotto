package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;

    public WinningLotto(String input) {
        this.winningLotto = Lotto.generateLottoByManual(input);
    }

    public int getMatchCount(Lotto lotto) {
        return (int) winningLotto.getLottoNumbers().stream()
                .filter(lotto.getLottoNumbers()::contains)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.isContain(lottoNumber);
    }
}
