package domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.Lottos;

public class AutoLottoGenerator implements LottoGenerator {
	private final int count;

	public AutoLottoGenerator(int count) {
		this.count = count;
	}

	@Override
	public List<Lotto> creatLottos() {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(createAutoLotto());
		}
		return new ArrayList<>(lottos);
	}

	private Lotto createAutoLotto() {
		Collections.shuffle(LOTTO_BUCKET);
		return Lotto.from(LOTTO_BUCKET.stream()
			.limit(Lotto.SIZE)
			.collect(Collectors.toUnmodifiableList()));
	}
}
