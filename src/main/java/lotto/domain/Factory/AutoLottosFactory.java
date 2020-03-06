package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosFactory implements LottosFactory {

	@Override
	public List<Lotto> create(LottoMoney lottoMoney) {
		List<Lotto> autoLottos = new ArrayList<>();
		for (int i = 0; i < lottoMoney.countPurchasedTickets(); i++) {
			autoLottos.add(AutoLottoFactory.create());
		}
		return autoLottos;
	}
}
