package lotto.domain;

import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingCounter;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final List<Lotto> lottos = new ArrayList<>();
    private final RatingCounter ratingCounter = new RatingCounter();

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void generateLottos(Ticket ticket) {
        for (int i = 0; i < ticket.getCount(); i++) {
            lottos.add(new Lotto(lottoMachine.generate()));
        }
    }

    public void scratchLotto(WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingCounter.update(Rating.getRating(match, hasBonusBall));
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
