package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.exceptions.ManualLottosFactoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManualLottosFactory implements LottosFactory {
	private final Lottos lottos;

	private ManualLottosFactory(Lottos lottos) {
		this.lottos = lottos;
	}

	public static ManualLottosFactory of(LottoMoney lottoMoney, List<String> strings) {
		checkLottosSizeIsValid(lottoMoney, strings);

		List<Lotto> manualLottos = new ArrayList<>();
		for (String string : strings) {
			manualLottos.add(Lotto.of(string));
		}
		return new ManualLottosFactory(Lottos.of(manualLottos));
	}

	private static void checkLottosSizeIsValid(LottoMoney lottoMoney, List<String> strings) {
		if (lottoMoney.canNotPurchase(strings.size())) {
			throw new ManualLottosFactoryException();
		}
	}

	@Override
	public Lottos create() {
		return lottos;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ManualLottosFactory that = (ManualLottosFactory) o;
		return Objects.equals(lottos, that.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottos);
	}

	@Override
	public String toString() {
		return "ManualLottosFactory{" +
				"lottos=" + lottos +
				'}';
	}
}
