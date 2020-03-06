package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;

import java.util.List;

public interface LottosFactory {
	List<Lotto> create(LottoMoney lottoMoney);
}
