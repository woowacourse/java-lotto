package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * 여러 개의 로또를 테스트하는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottosTest {
	@Test
	void calculate() {
		Lottos lottos = new Lottos(Arrays.asList(
				Lotto.of(1, 2, 5, 43, 44, 45),
				Lotto.of(1, 2, 5, 43, 44, 45),
				Lotto.of(1, 2, 3, 43, 44, 45)));
		WinningLotto winningLotto = new WinningLotto(Lotto.of(1, 2, 5, 43, 44, 45), LottoNumber.of(7));

		Map<LottoRank, Long> expected = new HashMap<>();
		expected.put(LottoRank.FIRST, 2L);
		expected.put(LottoRank.THIRD, 1L);

		assertThat(lottos.calculate(winningLotto)).isEqualTo(expected);
	}
}
