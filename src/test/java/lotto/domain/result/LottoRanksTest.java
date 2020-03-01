package lotto.domain.result;

import static lotto.domain.result.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRanksTest {

	@DisplayName("winningResult 객체를 제대로 생성하는지 확인")
	@Test
	void getResult() {
		LottoRanks results = new LottoRanks(Arrays.asList(FIRST, FIRST));
		WinningResult winningResult = results.calculateWinningResult();
		assertThat(winningResult.getWinningResult())
			.extractingByKeys(MISSING, FIFTH, FOURTH, THIRD, SECOND, FIRST)
			.containsExactly(0L, 0L, 0L, 0L, 0L, 2L);
	}
}