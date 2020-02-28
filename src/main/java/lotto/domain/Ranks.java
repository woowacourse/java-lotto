package lotto.domain;

import java.util.List;
import java.util.stream.Stream;

public class Ranks {
	private List<Rank> ranks;

	public Ranks(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public Stream<Rank> stream() {
		return ranks.stream();
	}
}
