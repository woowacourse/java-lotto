package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoGenerator implements LottoGenerator {
	private static final List<LottoNumber> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.values());

	@Override
	public Lotto generate() {
		Collections.shuffle(LOTTO_NUMBERS);
		return new Lotto(LOTTO_NUMBERS.stream()
				.limit(Lotto.SIZE)
				.collect(Collectors.toList()));
	}

	@Override
	public Lottos generate(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (; count > 0; --count) {
			lottos.add(generate());
		}
		return new Lottos(lottos);
	}
}
