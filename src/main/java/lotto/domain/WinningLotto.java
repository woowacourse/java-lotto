package lotto.domain;

public class WinningLotto {
    private final Lotto winningLotto;

    WinningLotto(final Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    int countOfMatchLottoNumber(Lotto lotto) {
        int countOfMatch = 0;
        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            countOfMatch += winningLotto.hasNumber(lottoNumber);
        }

        return countOfMatch;
    }
}
