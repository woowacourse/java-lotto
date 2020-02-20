package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PurchasedLottoTickets {
	private final List<SerialLottoNumber> purchasedLottoTickets;

	public PurchasedLottoTickets(final List<SerialLottoNumber> purchasedLottoTickets) {
		this.purchasedLottoTickets = Collections.unmodifiableList(purchasedLottoTickets);
	}

	public List<SerialLottoNumber> getPurchasedLottoTickets() {
		return Collections.unmodifiableList(purchasedLottoTickets);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchasedLottoTickets that = (PurchasedLottoTickets) o;
		return Objects.equals(purchasedLottoTickets, that.purchasedLottoTickets);
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchasedLottoTickets);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLottoNumbers winningLottoNumbers) {
		return purchasedLottoTickets.stream()
				.map(winningLottoNumbers::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}
}
