package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Lotto들을 List형태로 가지고 있는 일급 컬랙션
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lottos {
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "입력이 null일 수 없습니다.";
	private static final String SHOULD_BUY_MORE_THAN_ONE_LOTTO_EXCEPTION_MESSAGE = "로또는 한 장 이상 사야합니다.";
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> inputLottos) {
		Objects.requireNonNull(inputLottos, NULL_INPUT_EXCEPTION_MESSAGE);
		validateEmptyInput(inputLottos);
		this.lottos = Collections.unmodifiableList(inputLottos);
	}

	private void validateEmptyInput(final List<Lotto> inputLottos) {
		if (inputLottos.isEmpty()) {
			throw new InvalidLottosException(SHOULD_BUY_MORE_THAN_ONE_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	public Lottos add(final Lottos tailLottos) {
		List<Lotto> lottos = new ArrayList<>(this.lottos);
		lottos.addAll(tailLottos.lottos);
		return new Lottos(lottos);
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
