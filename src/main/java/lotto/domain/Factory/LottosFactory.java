package lotto.domain.Factory;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

public interface LottosFactory {
	Lottos create(LottoMoney lottoMoney);
}
