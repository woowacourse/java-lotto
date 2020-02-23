package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 여러 개의 로또를 가지는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoTicket {
	private static final String INVALID_LOTTO_NUMBERS_SIZE_MESSAGE = "로또 숫자가 없습니다.";

	private final List<Lotto> lottos;

	public LottoTicket(List<Lotto> lottos) {
		validate(lottos);
		this.lottos = lottos;
	}

	private void validate(List<Lotto> lottos) {
		if (Objects.isNull(lottos) || lottos.isEmpty()) {
			throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_SIZE_MESSAGE);
		}
	}

	public MatchResult matchAll(WinningLotto winningLotto) {
		return new MatchResult(lottos.stream()
				.map(winningLotto::match)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
	}

	public int size() {
		return lottos.size();
	}

	public List<Lotto> getLottos() {
		return lottos;
	}
}
