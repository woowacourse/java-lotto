package domain;

import static domain.Lotto.*;
import static domain.LottoNumber.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private static final int INITIAL_INDEX = 0;

	private final List<Integer> lottoNumbers = IntStream.rangeClosed(FIRST_LOTTO_NUMBER, LAST_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());

	public List<Lotto> generateLottosAsAuto(final Money money) {
		int autoLottoCount = money.findPurchaseLottoCount();
		return IntStream.range(INITIAL_INDEX, autoLottoCount)
			.mapToObj(index -> generateLottoAsAuto())
			.collect(Collectors.toUnmodifiableList());
	}

	private Lotto generateLottoAsAuto() {
		Collections.shuffle(lottoNumbers);
		return new Lotto(abstractLottoNumbersAsMuchAsLottoSize().stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList()));
	}

	private List<Integer> abstractLottoNumbersAsMuchAsLottoSize() {
		return Collections.unmodifiableList(lottoNumbers.subList(INITIAL_INDEX, FIXED_LOTTO_SIZE));
	}

	public List<Lotto> generateLottosAsManual(List<List<Integer>> inputManualLotto) {
		return inputManualLotto.stream()
			.map(this::generateLottoAsManual)
			.collect(Collectors.toList());
	}

	private Lotto generateLottoAsManual(List<Integer> manualLotto) {
		return new Lotto(manualLotto.stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList()));
	}
}
