package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.WinningLotto;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class Lotteries {

    private final List<Lotto> lottos = new ArrayList<>();

    public void addLottoByTicket(final LottoMachine lottoMachine, final int ticketCount) {
        for (int i = 0; i < ticketCount; i++) {
            lottos.add(Lotto.from(lottoMachine.generate()));
        }
    }

    public void addLottoByManual(final Lotto lotto) {
        lottos.add(lotto);
    }

    public RatingInfo scratchLotto(final WinningLotto winningLotto) {
        final RatingInfo ratingInfo = new RatingInfo();
        for (final Lotto lotto : lottos) {
            final int match = winningLotto.compareLottoNumber(lotto);
            final boolean hasBonusBall = winningLotto.compareBonusBall(lotto);
            ratingInfo.update(Rating.getRating(match, hasBonusBall));
        }
        return ratingInfo;
    }

    public List<Lotto> toList() {
        return Collections.unmodifiableList(lottos);
    }

}
