package lotto.domain.lottos;

import lotto.domain.lotto.Lotto;

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
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> inputLottos) {
		Objects.requireNonNull(inputLottos, "입력이 null일 수 없습니다.");
		this.lottos = Collections.unmodifiableList(inputLottos);
	}

	public Lottos concat(final Lottos tailLottos) {
		List<Lotto> lottos = new ArrayList<>(this.lottos);
		lottos.addAll(tailLottos.lottos);
		return new Lottos(lottos);
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
