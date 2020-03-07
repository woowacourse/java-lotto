package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
	private final List<Lotto> lottos;

	private Lottos(final List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos of(List<Lotto> inputs) {
		return new Lottos(inputs);
	}

	public List<Lotto> getLottos() {
		return Collections.unmodifiableList(lottos);
	}

	public List<WinningType> findMatchingWinningTypesWith(WinningLotto winningLotto) {
		return lottos.stream()
				.map(winningLotto::findMatchingWinningTypeWith)
				.collect(Collectors.toUnmodifiableList());
	}

	public Lottos add(Lottos other) {
		List<Lotto> serialLottoNumbers = new ArrayList<>(lottos);
		serialLottoNumbers.addAll(other.lottos);

		return new Lottos(serialLottoNumbers);
	}

	public int size() {
		return lottos.size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lottos that = (Lottos) o;
		return Objects.equals(lottos, that.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottos);
	}

	@Override
	public String toString() {
		return lottos.toString();
	}
}
