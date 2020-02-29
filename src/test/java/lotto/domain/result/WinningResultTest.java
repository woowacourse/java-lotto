package lotto.domain.result;

import static lotto.domain.result.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.Money;

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

	@DisplayName("당첨 정보를 통해 수익률 계산 테스트")
	@Test
	void calculateProfitsRate() {
		long expected = (2_000_000_000L + 5_000L + 50_000L) * 10L * 100 / (32 * Money.UNIT);
		assertThat(result.calculateProfitRate()).isEqualTo(expected);
	}

	@DisplayName("각 순위별 당첨 횟수 계한 기능 테스트")
	@ParameterizedTest
	@MethodSource("get_winning_rank_test_table")
	void findWinningCountTest(LottoRank rank, long winningCount) {
		assertThat(result.findWinningCount(rank)).isEqualTo(winningCount);
	}

	private static Stream<Arguments> get_winning_rank_test_table() {
		return Stream.of(
			Arguments.of(FIRST, 10L),
			Arguments.of(SECOND, 0L),
			Arguments.of(THIRD, 0L),
			Arguments.of(FOURTH, 10L),
			Arguments.of(FIFTH, 10L),
			Arguments.of(MISSING, 2L)
		);
	}
}
