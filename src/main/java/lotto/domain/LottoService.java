package lotto.domain;

import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.rating.RatingCounter;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final List<Lotto> lottos = new ArrayList<>();
    private final RatingCounter ratingCounter = new RatingCounter();

    public LottoService() {
        this(new RandomLottoMachine());
    }

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void addManualLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public void generateLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(lottoMachine.generate()));
        }
    }

    public void scratchLotto(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            ratingCounter.update(winningLotto.scratch(lotto));
        }
    }

    public double getEarningRate(final Money money) {
        return ratingCounter.totalSum() / money.getValue();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public RatingCounter getRatingCounter() {
        return ratingCounter;
    }
}
