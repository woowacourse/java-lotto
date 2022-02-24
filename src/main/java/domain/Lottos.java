package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public List<Rank> calculateRank(WinningLotto winningLotto) {
		return lottos.stream()
			.map(lotto -> winningLotto.calculateRank(lotto))
			.collect(Collectors.toUnmodifiableList());
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}

	@Override
	public String toString() {
		return "Lottos{" +
			lottos +
			'}';
	}
}
