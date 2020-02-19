package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = Collections.unmodifiableList(lottos);
	}

	public boolean isRightSize(int size) {
		return lottos.size() == size;
	}
}
