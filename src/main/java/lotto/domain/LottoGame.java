package lotto.domain;

import lotto.utils.LottosGenerator;

public class LottoGame {

    private final LottosGenerator lottosGenerator;

    public LottoGame(LottosGenerator lottosGenerator) {
        this.lottosGenerator = lottosGenerator;
    }

    public Lottos createLottos() throws IllegalArgumentException {
        return lottosGenerator.generate();
    }

    public WinningLotto createWinningLotto(LottoNumbers winningNumbers, LottoNumber bonusNumber) throws IllegalArgumentException {
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    public LottoGameResult calculateYield(Lottos myLottos, WinningLotto winningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();
        myLottos.lottos().stream()
                .map(winningLotto::findRank)
                .forEach(lottoGameResult::add);
        return lottoGameResult;
    }
}
