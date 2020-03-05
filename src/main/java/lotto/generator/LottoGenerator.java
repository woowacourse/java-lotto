package lotto.generator;

import lotto.domain.BettingInfo;
import lotto.domain.Lottos;

public interface LottoGenerator {
    Lottos createLottos(BettingInfo bettingInfo);
}
