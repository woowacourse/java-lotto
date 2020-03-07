package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.exceptions.ManualLottosFactoryException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManualLottosFactory implements LottosFactory {
	private final Lottos lottos;

	private ManualLottosFactory(Lottos lottos) {
		this.lottos = lottos;
	}

	public static ManualLottosFactory of(LottoMoney lottoMoney, String... strings) {
		return of(lottoMoney, Arrays.asList(strings));
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

}
