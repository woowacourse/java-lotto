package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.LottoFactory;

import java.util.*;
import java.util.stream.Collectors;

public class PurchasedLottos {
	private final List<Lotto> purchasedLottos;

	private PurchasedLottos(final List<Lotto> purchasedLottos) {
		this.purchasedLottos = purchasedLottos;
	}

	public static PurchasedLottos of(LottoMoney lottoMoney,
									 LottoFactory LottoFactory) {
		List<Lotto> purchasedLotto = new ArrayList<>();

		int count = lottoMoney.countPurchasedTickets();
		for (int i = 0; i < count; i++) {
			purchasedLotto.add(LottoFactory.createLotto());
		}

		return new PurchasedLottos(purchasedLotto);
	}

	public static PurchasedLottos of(List<String> inputs) {
		List<Lotto> purchasedLotto = inputs.stream()
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList());

		return new PurchasedLottos(purchasedLotto);
	}

	public static PurchasedLottos empty() {
		List<Lotto> lottos = new ArrayList<>();
		return new PurchasedLottos(lottos);
	}

	public List<Lotto> getPurchasedLottos() {
		return Collections.unmodifiableList(purchasedLottos);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLotto winningLotto) {
		return purchasedLottos.stream()
				.map(winningLotto::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public void add(String lotto) {
		purchasedLottos.add(Lotto.of(lotto));
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
