package lotto.domain.result;

import static lotto.domain.result.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.ticket.Money;

public class TotalResultTest {
	private TotalResult totalResult;

	@BeforeEach
	void setup() {
		List<LottoRank> list = new ArrayList<>(Arrays.asList(MISSING, MISSING));
		for (int i = 0; i < 10; i++) {
			list.add(FIRST);
			list.add(FOURTH);
			list.add(FIFTH);
		}
		totalResult = new TotalResult(new WinningResult(list), Money.valueOf(2_000_000_000));
	}

	@DisplayName("당첨 정보를 통해 수익률 계산 테스트")
	@Test
	void calculateProfitsRate() {
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L * 100 / 2_000_000_000;
		assertThat(totalResult.getProfitRate()).isEqualTo(expected);
	}
}
