package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosFactory implements LottosFactory {

	@Override
	public Lottos create(LottoMoney lottoMoney) {
		List<Lotto> autoLottos = new ArrayList<>();
		for (int i = 0; i < lottoMoney.countPurchasedTickets(); i++) {
			autoLottos.add(AutoLottoFactory.create());
		}
		return Lottos.of(autoLottos);
	}
}
