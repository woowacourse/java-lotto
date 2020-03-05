package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.LottoFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PurchasedLottos {
	private final List<Lotto> purchasedLottos;

	public PurchasedLottos(final List<Lotto> purchasedLottos) {
		this.purchasedLottos = Collections.unmodifiableList(purchasedLottos);
	}

	public static PurchasedLottos of(LottoMoney lottoMoney,
									 LottoFactory LottoFactory) {
		List<Lotto> purchasedSerialLottoNumbers = new ArrayList<>();

		int count = lottoMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedSerialLottoNumbers.add(LottoFactory.createSerialLottoNumber());
		}

		return new PurchasedLottos(purchasedSerialLottoNumbers);
	}

	public List<Lotto> getPurchasedLottos() {
		return Collections.unmodifiableList(purchasedLottos);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLotto winningLotto) {
		return purchasedLottos.stream()
				.map(winningLotto::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public PurchasedLottos addAll(PurchasedLottos other) {
		List<Lotto> serialLottoNumbers = new ArrayList<>(purchasedLottos);
		serialLottoNumbers.addAll(other.purchasedLottos);

		return new PurchasedLottos(serialLottoNumbers);
	}

	public int size() {
		return purchasedLottos.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchasedLottos that = (PurchasedLottos) o;
		return Objects.equals(purchasedLottos, that.purchasedLottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchasedLottos);
	}

	@Override
	public String toString() {
		return purchasedLottos.toString();
	}
}
