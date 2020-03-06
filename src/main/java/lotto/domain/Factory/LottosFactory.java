package lotto.domain.Factory;

import lotto.domain.LottoMoney;
import lotto.domain.PurchasedLottos;

public interface LottosFactory {
	PurchasedLottos create(LottoMoney lottoMoney);
}
