package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.WinningLotto;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class LottoRepository {

    private final List<Lotto> lottos = new ArrayList<>();

    public void generateLottoByTicket(final LottoMachine lottoMachine, final int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            lottos.add(Lotto.from(lottoMachine.generate()));
        }
    }

    public void generateLottoByManual(final Lotto lotto) {
        lottos.add(lotto);
    }

    public RatingInfo scratchLotto(final WinningLotto winningLotto) {
        RatingInfo ratingInfo = new RatingInfo();
        for (Lotto lotto : lottos) {
            int match = winningLotto.compareLottoNumber(lotto);
            boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingInfo.update(Rating.getRating(match, hasBonusBall));
        }
        return ratingInfo;
    }

    public List<Lotto> toList() {
        return Collections.unmodifiableList(lottos);
    }

}
