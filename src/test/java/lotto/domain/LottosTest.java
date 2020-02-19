package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

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
