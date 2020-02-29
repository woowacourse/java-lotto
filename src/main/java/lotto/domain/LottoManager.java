package lotto.domain;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public static Lottos generateLottoByAmount(int amount, NumberGenerator lottoNumberGenerator) {
		return IntStream.range(0, amount)
			.mapToObj(x -> generateSingleLotto(lottoNumberGenerator))
			.collect(Collectors.collectingAndThen(Collectors.toList(), Lottos::new));
	}

	public static Lottos generateManualLotto(int manualLottoAmount) {
		return InputView.inputAsManualLottoNumbers(manualLottoAmount)
			.stream()
			.map(NumberParser::lottoNumberParse)
			.map(Lotto::new)
			.collect(Collectors.collectingAndThen(Collectors.toList(), Lottos::new));
	}
}
