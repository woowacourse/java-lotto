package lotto.domain;

import lotto.domain.lotto.LottoRepository;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.rating.Rating;
import lotto.domain.rating.RatingInfo;

public class LottoService {

    public LottoRepository getLotto(final LottoRepository lottoRepository,
        final LottoMachine lottoMachine, final int count) {
        lottoRepository.generateLottoByTicket(lottoMachine, count);
        return lottoRepository;
    }

}

