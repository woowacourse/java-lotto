package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 클래스 이름 : Lottos.java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(final List<Lotto> inputLottos) {
		Objects.requireNonNull(inputLottos, "입력이 null일 수 없습니다.");
		validateEmptyInput(inputLottos);
		this.lottos = Collections.unmodifiableList(inputLottos);
	}

	private void validateEmptyInput(final List<Lotto> inputLottos) {
		if (inputLottos.isEmpty()) {
			throw new IllegalArgumentException("로또는 한 장 이상 사야합니다.");
		}
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
