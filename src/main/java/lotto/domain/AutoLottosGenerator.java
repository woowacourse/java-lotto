package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottosGenerator implements LottosGenerator {
	private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.values());

	public Lotto create() {
		Collections.shuffle(LOTTO_NUMBERS);
		return LOTTO_NUMBERS.stream()
				.limit(Lotto.SIZE)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
	}

	@Override
	public Lottos generate(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (; count > 0; --count) {
			lottos.add(create());
		}
		return new Lottos(lottos);
	}
}
