package domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public Map<Rank, Long> countRank(WinningLotto winningLotto) {
		EnumMap<Rank, Long> ranks = Rank.getMap();

		lottos.stream()
			.map(lotto -> winningLotto.calculateRank(lotto))
			.filter(rank -> !rank.isNothing())
			.collect(groupingBy(Rank::getRank, counting()))
			.forEach((key, value) -> ranks.merge(key, value, (v1, v2) -> v2));
		return ranks;
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
