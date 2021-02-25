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

    public LottoQuantity createLottoQuantity(Money money, String fixedLottoQuantity) throws IllegalArgumentException {
        return new LottoQuantity(money, fixedLottoQuantity);
    }

    public void buyLottos(Money money, LottoGenerator lottoGenerator) throws IllegalArgumentException {
        IntStream.range(0, money.countLotto())
                .mapToObj(i -> lottoGenerator.generate())
                .forEach(myLottos::add);
    }

    public WinningLotto createWinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber)
            throws IllegalArgumentException {
        return new WinningLotto(new Lotto(lottoNumbers), bonusNumber);
    }

    public LottoGameResult calculateLottoGameResult(WinningLotto winningLotto) {
        return myLottos().compareWithWinningLotto(winningLotto);
    }
}
