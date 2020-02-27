package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lotto.domain.exception.InvalidLottosException;

public class Lottos implements Iterable<Lotto> {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		validateNullOrEmpty(lottos);
		this.lottos = new ArrayList<>(lottos);
	}

	private void validateNullOrEmpty(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new InvalidLottosException(InvalidLottosException.NULL_OR_EMPTY);
		}
	}

	public static Lottos merge(Lottos lottos1, Lottos lottos2) {
		List<Lotto> newLottos = new LinkedList<>(lottos1.lottos);
		newLottos.addAll(lottos2.lottos);
		return new Lottos(newLottos);
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}
}
