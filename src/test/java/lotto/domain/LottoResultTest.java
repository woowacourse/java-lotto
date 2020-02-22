package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LottoResultTest {
	@Test
	void LottoResult() {
		// given
		Map<WinningType, Integer> map = new HashMap<>();
		map.put(WinningType.FIFTH_PLACE, 5);
		map.put(WinningType.NONE, 4);
		map.put(WinningType.FIRST_PLACE, 0);

		// when
		LottoResult result = new LottoResult(map);

		// then
		Assertions.assertThat(result.getLottoResult())
				.isEqualTo(map);
	}
}
