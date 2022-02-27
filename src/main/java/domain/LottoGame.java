package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int EMPTY = 0;
    private static final int NO_YIELD = 0;
    private static final int SUM_BASE = 0;

    private Lotto winningLotto;
    private LottoNumber bonusNumber;
    private Lottos lottos;

    public LottoGame() {
    }

    public LottoGame(List<Lotto> lottos) {
        this.lottos = new Lottos(lottos);
    }

    public Lottos buyLotto(Money money) {
        int lottoAmount = money.money() / LOTTO_PRICE;
        lottos = Lottos.buyLottos(lottoAmount);
        return lottos;
    }

    public void enterWinningLottoNumbersAndBonusNumber(List<Integer> notVerifiedWinningLottoNumbers
            , int notVerifiedBonusNumber) {
        List<LottoNumber> winningLottoNumbers = notVerifiedWinningLottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.winningLotto = new Lotto(winningLottoNumbers);
        this.bonusNumber = new LottoNumber(notVerifiedBonusNumber);
    }

    public void makeResult(List<Integer> notVerifiedWinningNumbers, Integer notVerifiedBonusNumber) {
        List<LottoNumber> winningNumbers = notVerifiedWinningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        lottos.compareAllLotto(winningNumbers, new LottoNumber(notVerifiedBonusNumber));
    }

    public double getYield() {
        if (lottos.getSize() == EMPTY) {
            return NO_YIELD;
        }

        int prize = Arrays.stream(Rewards.values())
                .map(Rewards::calculateYield)
                .reduce(SUM_BASE, Integer::sum);
        return (float) prize / (LOTTO_PRICE * lottos.getSize());
    }

    public Lottos getLottos() {
        return lottos;
    }
}
