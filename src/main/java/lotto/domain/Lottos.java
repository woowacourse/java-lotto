package lotto.domain;

import lotto.dto.LottoDtos;
import lotto.exception.LottosException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> lottos) {
		validateNullAndEmpty(lottos);
		this.lottos = lottos;
	}

	private void validateNullAndEmpty(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new LottosException();
		}
	}

	public LottoDtos makeLottoDtos() {
		return new LottoDtos(lottos.stream()
				.map(Lotto::makeLottoDto)
				.collect(Collectors.toList()));
	}

	public List<WinningPrize> findAllLottoPrizes(WinningLotto winningNumber) {
		return lottos.stream()
				.map(lotto -> lotto.findLottoPrize(winningNumber))
				.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Lottos lottos1 = (Lottos) o;
		return Objects.equals(lottos, lottos1.lottos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottos);
	}
}
