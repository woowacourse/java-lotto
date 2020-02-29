package lotto.domain.result;

import lotto.domain.number.SerialLottoNumber;
import lotto.util.ListBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTickets {
	private final List<SerialLottoNumber> lottoTickets;

	LottoTickets(List<SerialLottoNumber> lottoTickets) {
		this.lottoTickets = Collections.unmodifiableList(lottoTickets);
	}

	public static LottoTickets merge(final LottoTickets manualLottoTickets, final LottoTickets autoLottoTickets) {
		return new LottoTickets(ListBuilder.merge(manualLottoTickets.lottoTickets, autoLottoTickets.lottoTickets));
	}

	public List<Rank> findResult(Winning winning) {
		return lottoTickets.stream()
				.map(winning::findMatchingRank)
				.collect(Collectors.toUnmodifiableList());
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
