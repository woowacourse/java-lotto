package domain;

import static domain.Lotto.*;
import static domain.LottoNumber.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private final List<Integer> lottoNumbers = IntStream.rangeClosed(FIRST_LOTTO_NUMBER, LAST_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());

	public List<Lotto> generateLottos(final Money money, List<List<Integer>> inputManualLotto) {
		List<Lotto> lottos = generateLottosAsManual(inputManualLotto);
		lottos.addAll(generateLottosAsAuto(money));
		return lottos;
	}

	public List<Lotto> generateLottosAsAuto(final Money money) {
		int autoLottoCount = money.findPurchaseLottoCount();
		return IntStream.range(0, autoLottoCount)
			.mapToObj(index -> generateLottoAsAuto())
			.collect(Collectors.toUnmodifiableList());
	}

	private Lotto generateLottoAsAuto() {
		Collections.shuffle(lottoNumbers);
		return generateLotto(lottoNumbers.subList(0, FIXED_LOTTO_SIZE));
	}

	private Lotto generateLotto(List<Integer> numbers) {
		return new Lotto(numbers.stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList()));
	}

	public List<Lotto> generateLottosAsManual(List<List<Integer>> inputManualLotto) {
		return inputManualLotto.stream()
			.map(this::generateLottoAsManual)
			.collect(Collectors.toList());
	}

	private Lotto generateLottoAsManual(List<Integer> manualLotto) {
		return generateLotto(manualLotto);
	}
}
