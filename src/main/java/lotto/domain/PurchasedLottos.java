package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PurchasedLottos {
	private final List<Lotto> purchasedLottos;

	private PurchasedLottos(final List<Lotto> purchasedLottos) {
		this.purchasedLottos = purchasedLottos;
	}

	public static PurchasedLottos of(String... inputs) {
		List<Lotto> purchasedLotto = Stream.of(inputs)
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList());

		return new PurchasedLottos(purchasedLotto);
	}

	public static PurchasedLottos of(List<Lotto> inputs) {
		return new PurchasedLottos(inputs);
	}

	public List<Lotto> getPurchasedLottos() {
		return Collections.unmodifiableList(purchasedLottos);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLotto winningLotto) {
		return purchasedLottos.stream()
				.map(winningLotto::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public PurchasedLottos add(PurchasedLottos other) {
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
