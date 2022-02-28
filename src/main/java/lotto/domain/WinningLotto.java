package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;

    private WinningLotto(Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLotto generateWinningLottoByConsole(String consoleInput) {
        return new WinningLotto(Lotto.generateLottoByConsole(consoleInput));
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningLotto.isContain(lottoNumber);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
}
