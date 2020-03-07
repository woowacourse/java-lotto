package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosFactory implements LottosFactory {
	private final Lottos lottos;

	private AutoLottosFactory(final Lottos lottos) {
		this.lottos = lottos;
	}

	public static AutoLottosFactory of(LottoMoney lottoMoney) {
		List<Lotto> autoLottos = new ArrayList<>();
		for (int i = 0; i < lottoMoney.countPurchasedTickets(); i++) {
			autoLottos.add(AutoLottoFactory.create());
		}
		return new AutoLottosFactory(Lottos.of(autoLottos));
	}

	@Override
	public Lottos create() {
		return lottos;
	}
}
