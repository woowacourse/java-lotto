package domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = Objects.requireNonNull(lottos,"[ERROR] lottos가 null 입니다.");
	}

	public Map<Rank, Long> countRank(WinningLotto winningLotto) {
		EnumMap<Rank, Long> ranks = LottoResult.getRankMap();

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
}
