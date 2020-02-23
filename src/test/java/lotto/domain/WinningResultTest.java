package lotto.domain;

import static lotto.domain.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
	private WinningResult result;

	@BeforeEach
	void setup() {
		List<LottoRank> list = new ArrayList<>(Arrays.asList(MISSING, MISSING));
		for (int i = 0; i < 10; i++) {
			list.add(FIRST);
			list.add(FOURTH);
			list.add(FIFTH);
		}
		result = new WinningResult(list);
	}

	@DisplayName("당첨 결과에 포함되어있는 총 삼금 계산 확인")
	@Test
	void calculateTotalPrize() {
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L;
		assertThat(result.calculateTotalMoney()).extracting("money").isEqualTo(expected);
	}
}
