package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;
import lotto.domain.result.Rank;
import lotto.domain.result.Winning;
import lotto.util.ListBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTickets {
	private final List<SerialLottoNumber> lottoTickets;

	LottoTickets(final List<SerialLottoNumber> lottoTickets) {
		this.lottoTickets = Collections.unmodifiableList(lottoTickets);
	}

	public static LottoTickets merge(final LottoTickets... someLottoTickets) {
		return new LottoTickets(mergeLottoTickets(someLottoTickets));
	}

	private static List<SerialLottoNumber> mergeLottoTickets(final LottoTickets... someLottoTickets) {
		return Arrays.stream(someLottoTickets)
				.map(lottoTickets -> lottoTickets.lottoTickets)
				.reduce(new ArrayList<>(), ListBuilder::merge);
	}

	public Map<Rank, Integer> findResult(Winning winning) {
		List<Rank> ranks = lottoTickets.stream()
				.map(winning::findMatchingRank)
				.collect(Collectors.toList());

		return createRankToCount(ranks);
	}

	private Map<Rank, Integer> createRankToCount(final List<Rank> ranks) {
		return Arrays.stream(Rank.values())
				.collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> Collections.frequency(ranks, rank)));
	}

	public List<SerialLottoNumber> getLottoTickets() {
		return lottoTickets;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoTickets that = (LottoTickets) o;
		return Objects.equals(lottoTickets, that.lottoTickets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoTickets);
	}
}
