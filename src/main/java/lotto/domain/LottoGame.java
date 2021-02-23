package lotto.domain;

import lotto.utils.LottoGenerator;

import java.util.stream.IntStream;

public class LottoGame {

    private final Lottos myLottos;

    public LottoGame() {
        myLottos = new Lottos();
    }

    public Lottos myLottos() {
        return myLottos;
    }

    public void buyLottos(Money money, LottoGenerator lottoGenerator) {
        IntStream.range(0, money.countLotto())
                .mapToObj(i -> lottoGenerator.generate())
                .forEach(myLottos::add);
    }

    public LottoGameResult calculateLottoGameResult(WinningLotto winningLotto) {
        return myLottos().compareWithWinningLotto(winningLotto);
    }
}
