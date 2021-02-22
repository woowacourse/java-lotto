package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lotto.domain.WinningLotto;
import lotto.domain.lottogenerator.RandomLottoGenerator;
import lotto.exception.LessThanLottoPriceException;
import lotto.model.LottoResult;
import lotto.model.Money;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos buyLotto(Money purchaseAmount) {
        if (purchaseAmount.isLessThan(Lotto.LOTTO_PRICE)) {
            throw new LessThanLottoPriceException();
        }
        int numOfAvailableLotto = purchaseAmount.getPrice() / Lotto.LOTTO_PRICE;
        List<Lotto> availableLotto = Stream.iterate(0, i -> i+1)
            .map(i -> Lotto.generatedBy(new RandomLottoGenerator()))
            .limit(numOfAvailableLotto)
            .collect(Collectors.toList());
        return new Lottos(availableLotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getNumOfLottos() {
        return this.getLottos().size();
    }

    public LottoResult match(WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            lottoResult.addRank(winningLotto.match(lotto));
        }
        return lottoResult;
    }
}
