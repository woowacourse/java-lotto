package lotto.domain.result;

import static lotto.domain.result.LottoRank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankTest {
	@DisplayName("볼 매칭 갯수, 보너스볼 유무에 따른 등수 반환 확인")
	@ParameterizedTest
	@MethodSource("findRank")
	void findRankTest(int matchCount, boolean hasBonusBall, LottoRank expected) {
		assertThat(ofValue(matchCount, hasBonusBall)).isEqualTo(expected);
	}

	private static Stream<Arguments> findRank() {
		return Stream.of(
			Arguments.of(0, true, MISSING),
			Arguments.of(2, true, MISSING),
			Arguments.of(3, true, FIFTH),
			Arguments.of(3, false, FIFTH),
			Arguments.of(4, true, FOURTH),
			Arguments.of(4, false, FOURTH),
			Arguments.of(5, false, THIRD),
			Arguments.of(5, true, SECOND),
			Arguments.of(6, false, FIRST)
		);
	}

	@DisplayName("등수별 당첨 매수 만큼 상금 계산이 이뤄지는지 테스트")
	@ParameterizedTest
	@MethodSource("calculateTotalPrize")
	void calculateFirstPrize1(LottoRank rank, int multiplier, long expected) {
		assertThat(rank.calculateTotalMoney(multiplier)).extracting("money").isEqualTo(expected);
	}

	private static Stream<Arguments> calculateTotalPrize() {
		return Stream.of(
			Arguments.of(MISSING, 10, 0L),
			Arguments.of(FIFTH, 10, 50_000L),
			Arguments.of(FOURTH, 10, 500_000L),
			Arguments.of(THIRD, 10, 15_000_000L),
			Arguments.of(SECOND, 10, 300_000_000L),
			Arguments.of(FIRST, 10, 20_000_000_000L)
		);
	}

	@DisplayName("상금 적은 순서대로 순위 리스트 가져오는 기능 테스트")
	@Test
	void getValuesOfRanksInAscendingOrder() {
		List<LottoRank> lottoRanks = valuesAscendingOrder();
		assertThat(lottoRanks).containsExactly(MISSING, FIFTH, FOURTH, THIRD, SECOND, FIRST);
	}
}
