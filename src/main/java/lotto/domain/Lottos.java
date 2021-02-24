package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lotto.domain.Rank.getRankByGivenMatchInformation;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public List<Rank> getResultsBasedOn(WinningLotto winningLotto) {
		List<Rank> ranks = new ArrayList<>();
		for (Lotto lotto : lottos) {
			int matches = winningLotto.countMatchingNumbersWith(lotto);
			boolean bonusMatch = winningLotto.hasBonusMatchWith(lotto);
			Rank rankByGivenMatchInformation = getRankByGivenMatchInformation(matches, bonusMatch);
			ranks.add(rankByGivenMatchInformation);
		}
		return ranks;
	}

	public List<Lotto> toList() {
		return Collections.unmodifiableList(lottos);
	}

	public Lottos merge(Lottos that) {
		List<Lotto> newLottos = Stream.concat(this.lottos.stream(), that.lottos.stream())
				.collect(Collectors.toList());
		return new Lottos(newLottos);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lottos lottos1 = (Lottos) o;
		return lottos.equals(lottos1.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottos);
	}
}
