package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(int numberOfLotto) {
		lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoFactory.create());
		}
	}

	public int getSize() {
		return lottos.size();
	}
}
