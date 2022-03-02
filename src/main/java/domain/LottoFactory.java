package domain;

import static domain.Lotto.*;
import static domain.LottoNumber.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

	private final List<Integer> lottoNumbers = IntStream.rangeClosed(FIRST_LOTTO_NUMBER, LAST_LOTTO_NUMBER)
		.boxed()
		.collect(Collectors.toList());

	public List<Lotto> generateLottos(final int autoLottoCount, final List<List<Integer>> inputManualLotto) {
		List<Lotto> lottos = new ArrayList<>();
		generateLottosAsManual(lottos, inputManualLotto);
		generateLottosAsAuto(lottos, autoLottoCount);
		return lottos;
	}

	private void generateLottosAsAuto(List<Lotto> lottos, final int autoLottoCount) {
		lottos.addAll(IntStream.range(0, autoLottoCount)
			.mapToObj(index -> generateLottoAsAuto())
			.collect(Collectors.toUnmodifiableList()));
	}

	private Lotto generateLottoAsAuto() {
		Collections.shuffle(lottoNumbers);
		return generateLotto(lottoNumbers.subList(0, FIXED_LOTTO_SIZE));
	}

	private Lotto generateLotto(final List<Integer> numbers) {
		return new Lotto(numbers.stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList()));
	}

	private void generateLottosAsManual(List<Lotto> lottos,
		final List<List<Integer>> inputManualLotto) {
		if (inputManualLotto == null || inputManualLotto.isEmpty()) {
			return;
		}
		lottos.addAll(inputManualLotto.stream()
			.map(this::generateLottoAsManual)
			.collect(Collectors.toList()));
	}

	private Lotto generateLottoAsManual(final List<Integer> manualLotto) {
		return generateLotto(manualLotto);
	}

}
