package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lottos implements Iterable<Lotto> {
	private final List<Lotto> lottos;

	Lottos(List<Lotto> lottos) {
		validateNullOrEmpty(lottos);
		this.lottos = new ArrayList<>(lottos);
	}

	private void validateNullOrEmpty(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new InvalidLottosException(InvalidLottosException.NULL_OR_EMPTY);
		}
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}
}
