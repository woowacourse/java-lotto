package domain.generator;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.Lottos;

public class AutoLottoGenerator implements LottoGenerator {
	private final int count;
	private final int size;

	public AutoLottoGenerator(int count, int size) {
		this.count = count;
		this.size = size;
	}

	@Override
	public Lottos creatLottos() {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(createAutoLotto(size));
		}
		return new Lottos(lottos);
	}
}
