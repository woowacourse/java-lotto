package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.utils.NumberGenerator;
import lotto.utils.NumberParser;
import lotto.view.InputView;

public class LottoManager {
	private static final Money LOTTO_PRICE = new Money(1_000);

	public static long calculateLottoAmount(Money money) {
		return money.getQuotient(LOTTO_PRICE);
	}

	public static Lotto generateSingleLotto(NumberGenerator lottoNumberGenerator) {
		return new Lotto(lottoNumberGenerator.generate());
	}

	public static List<Lotto> generateLottoByAmount(int amount, NumberGenerator lottoNumberGenerator) {
		return IntStream.range(0, amount)
			.mapToObj(x -> generateSingleLotto(lottoNumberGenerator))
			.collect(Collectors.toList());
	}

	public static List<Lotto> generateManualLotto(int manualLottoAmount) {
		return InputView.inputAsManualLottoNumbers(manualLottoAmount)
			.stream()
			.map(NumberParser::lottoNumberParse)
			.map(Lotto::new)
			.collect(Collectors.toList());
	}

	public static List<Lotto> concatTotalLotto(List<Lotto> autoLotto, List<Lotto> manualLotto) {
		autoLotto.addAll(manualLotto);
		return autoLotto;
	}
}
