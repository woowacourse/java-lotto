package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public Map<Rank, Integer> countRank(WinningLotto winningLotto) {
		Map<Rank, Integer> rankCounts = Rank.getMap();

		lottos.stream()
			.map(lotto -> winningLotto.calculateRank(lotto))
			.filter(rank -> !rank.isNothing())
			.forEach(rank -> rankCounts.replace(rank, rankCounts.get(rank) + 1));
		return Collections.unmodifiableMap(rankCounts);
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
