package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 여러 개의 로또를 가지는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoTicket {
	private static final String INVALID_LOTTO_NUMBERS_SIZE_MESSAGE = "로또 숫자가 없습니다.";

	private final List<Lotto> lottoTicket;

	public LottoTicket(List<Lotto> lottoTicket) {
		validate(lottoTicket);
		this.lottoTicket = Collections.unmodifiableList(lottoTicket);
	}

	public static LottoTicket concat(LottoTicket... tickets) {
		return Stream.of(tickets)
				.flatMap(ticket -> ticket.lottoTicket.stream())
				.collect(Collectors.collectingAndThen(Collectors.toList(), LottoTicket::new));
	}

	private void validate(List<Lotto> lottos) {
		if (Objects.isNull(lottos)) {
			throw new IllegalArgumentException(INVALID_LOTTO_NUMBERS_SIZE_MESSAGE);
		}
	}

	public MatchResult matchAll(WinningLotto winningLotto) {
		return lottoTicket.stream()
				.map(winningLotto::match)
				.collect(Collectors.collectingAndThen(Collectors.groupingBy(Function.identity(), Collectors.counting()),
						MatchResult::new));
	}

	public int size() {
		return lottoTicket.size();
	}

	public List<Lotto> getLottoTicket() {
		return lottoTicket;
	}
}
