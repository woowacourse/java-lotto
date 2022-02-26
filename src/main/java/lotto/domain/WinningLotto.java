package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;

    public WinningLotto(String input) {
        this.winningLotto = Lotto.generateLottoByManual(input);
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.isContain(lottoNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
}
