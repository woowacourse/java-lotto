package lotto.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Lottos {
	public static final String INVALID_LOTTO_SIZE_MESSAGE = "로또가 존재하지 않습니다.";

	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		validate(lottos);
		this.lottos = lottos;
	}

	private void validate(List<Lotto> lottos) {
		if (lottos == null || lottos.isEmpty()) {
			throw new IllegalArgumentException(INVALID_LOTTO_SIZE_MESSAGE);
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
