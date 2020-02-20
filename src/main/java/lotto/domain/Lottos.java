package lotto.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lotto.domain.exception.InvalidLottosException;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		validate(lottos);
		this.lottos = lottos;
	}

	private void validate(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new InvalidLottosException("하나 이상의 로또가 필요합니다.");
		}
	}

	public LottoResults match(WinningLotto winningLotto) {
		return lottos.stream()
				.map(winningLotto::match)
				.collect(Collectors.collectingAndThen(Collectors.toList(), LottoResults::new));
	}

	public void forEach(Consumer<Lotto> action) {
		lottos.forEach(action);
	}
}
