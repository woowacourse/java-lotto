package lotto.domain.manager;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;
import lotto.domain.statistics.WinningLotto;

public class LottoManager {

    private final RatingInfo ratingInfo = new RatingInfo();
    private final LottoRepository lottoRepository = new LottoRepository();
    private LottoMachine lottoMachine;

    public LottoManager(final LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoRepository buyLotto(final Ticket ticket) {
        lottoRepository.generateLottoByTicket(lottoMachine, ticket.getCount());
        return lottoRepository;
    }

    public RatingInfo scratchLotto(WinningLotto winningLotto) {
        for (Lotto lotto : lottoRepository.toList()) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingInfo.update(Rating.getRating(match, hasBonusBall));
        }
        return ratingInfo;
    }
}

