package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.SerialLottoNumberFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PurchasedSerialLottoNumbers {
	private final List<Lotto> purchasedSerialLottoNumbers;

	public PurchasedSerialLottoNumbers(final List<Lotto> purchasedSerialLottoNumbers) {
		this.purchasedSerialLottoNumbers = Collections.unmodifiableList(purchasedSerialLottoNumbers);
	}

	public static PurchasedSerialLottoNumbers of(PurchaseMoney purchaseMoney,
												 SerialLottoNumberFactory SerialLottoNumberFactory) {
		List<Lotto> purchasedSerialLottoNumbers = new ArrayList<>();

		int count = purchaseMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedSerialLottoNumbers.add(SerialLottoNumberFactory.createSerialLottoNumber());
		}

		return new PurchasedSerialLottoNumbers(purchasedSerialLottoNumbers);
	}

	public List<Lotto> getPurchasedSerialLottoNumbers() {
		return Collections.unmodifiableList(purchasedSerialLottoNumbers);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLottoNumbers winningLottoNumbers) {
		return purchasedSerialLottoNumbers.stream()
				.map(winningLottoNumbers::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public PurchasedSerialLottoNumbers addAll(PurchasedSerialLottoNumbers other) {
		List<Lotto> serialLottoNumbers = new ArrayList<>(purchasedSerialLottoNumbers);
		serialLottoNumbers.addAll(other.purchasedSerialLottoNumbers);

		return new PurchasedSerialLottoNumbers(serialLottoNumbers);
	}

	public int size() {
		return purchasedSerialLottoNumbers.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchasedSerialLottoNumbers that = (PurchasedSerialLottoNumbers) o;
		return Objects.equals(purchasedSerialLottoNumbers, that.purchasedSerialLottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchasedSerialLottoNumbers);
	}

	@Override
	public String toString() {
		return purchasedSerialLottoNumbers.toString();
	}
}
