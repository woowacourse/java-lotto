package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoManager {
	private static final int LOTTO_PRICE = 1000;
	private static final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

	public static int calculateLottoAmount(Money money) {
		return money.getMoney() / LOTTO_PRICE;
	}

	public static Lotto generateSingleLotto() {
		return new Lotto(lottoNumberGenerator.generate());
	}

	public static List<Lotto> generateLottoByAmount(int amount) {
		return IntStream.range(0, amount)
			.mapToObj(x -> generateSingleLotto())
			.collect(Collectors.toList());
	}
}
