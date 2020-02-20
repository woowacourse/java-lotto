package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import lotto.dto.LottoDtos;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> lottos) {
		validateNullAndEmpty(lottos);
		this.lottos = lottos;
	}

	private void validateNullAndEmpty(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	public LottoDtos makeLottoDtos() {
		return new LottoDtos(lottos.stream()
			.map(Lotto::makeLottoDto)
			.collect(Collectors.toList()));
	}

	public List<WinningPrize> findAllLottoPrizes(WinningNumber winningNumber) {
		return lottos.stream()
			.map(lotto -> lotto.findLottoPrize(winningNumber))
			.collect(Collectors.toList());
	}
}
