package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public Lottos concat(Lottos other) {
		lottos.addAll(other.lottos);
		return this;
	}

	public Stream<Lotto> stream() {
		return lottos.stream();
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}
}
