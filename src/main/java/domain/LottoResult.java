package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class LottoResult {
	private final List<Rank> ranks;

	public LottoResult(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public Map<Rank, Integer> countRank() {
		Map<Rank, Integer> rankCounts = new LinkedHashMap<>();
		Arrays.asList(Rank.values()).stream()
			.filter(Rank::isNotNothing)
			.forEach(rank -> rankCounts.put(rank, count(rank)));
		return rankCounts;
	}

	private int count(Rank rank) {
		return Collections.frequency(ranks, rank);
	}

	public int calculateTotalProfit() {
		return ranks.stream()
			.mapToInt(rank -> rank.getMoney())
			.sum();
	}
}
