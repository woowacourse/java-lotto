package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AutoLottosFactory that = (AutoLottosFactory) o;
		return Objects.equals(lottos, that.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottos);
	}

	@Override
	public String toString() {
		return "AutoLottosFactory{" +
				"lottos=" + lottos +
				'}';
	}
}
