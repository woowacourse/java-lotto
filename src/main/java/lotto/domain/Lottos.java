package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.exception.InvalidLottosException;

public class Lottos implements Iterable<Lotto> {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		validate(lottos);
		this.lottos = Collections.unmodifiableList(new ArrayList<>(lottos));
	}

	private void validate(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new InvalidLottosException();
		}
	}

	public LottoResults match(WinningLotto winningLotto) {
		return lottos.stream()
				.map(winningLotto::match)
				.collect(Collectors.collectingAndThen(Collectors.toList(), LottoResults::new));
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}
}
