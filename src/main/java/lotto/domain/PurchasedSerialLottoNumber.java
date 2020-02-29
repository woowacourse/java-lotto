package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.SerialLottoNumberFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PurchasedSerialLottoNumber {
	private final List<SerialLottoNumber> purchasedSerialLottoNumbers;

	public PurchasedSerialLottoNumber(final List<SerialLottoNumber> purchasedSerialLottoNumbers) {
		this.purchasedSerialLottoNumbers = Collections.unmodifiableList(purchasedSerialLottoNumbers);
	}

	public static PurchasedSerialLottoNumber of(PurchaseMoney purchaseMoney,
												SerialLottoNumberFactory SerialLottoNumberFactory) {
		List<SerialLottoNumber> purchasedSerialLottoNumbers = new ArrayList<>();

		int count = purchaseMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedSerialLottoNumbers.add(SerialLottoNumberFactory.createSerialLottoNumber());
		}

		return new PurchasedSerialLottoNumber(purchasedSerialLottoNumbers);
	}

	public List<SerialLottoNumber> getPurchasedSerialLottoNumbers() {
		return Collections.unmodifiableList(purchasedSerialLottoNumbers);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLottoNumbers winningLottoNumbers) {
		return purchasedSerialLottoNumbers.stream()
				.map(winningLottoNumbers::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public PurchasedSerialLottoNumber addAll(PurchasedSerialLottoNumber other) {
		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>(purchasedSerialLottoNumbers);
		serialLottoNumbers.addAll(other.purchasedSerialLottoNumbers);

		return new PurchasedSerialLottoNumber(serialLottoNumbers);
	}

	public int size() {
		return purchasedSerialLottoNumbers.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchasedSerialLottoNumber that = (PurchasedSerialLottoNumber) o;
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
