package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StatisticTest {
	@Test
	@DisplayName("맞힌 수와 매칭되는 Enum을 반환")
	void getRankTest() {
		Statistic rank = Statistic.getRank(3,false);
		assertThat(rank).isEqualTo(Statistic.THREE);
	}

	@ParameterizedTest
	@CsvSource(value = {"5,true,3000000", "4,false,50000"})
	@DisplayName("수익을 정상적으로 반환하는 지")
	void profitTest(int match, boolean isBouns, int profit) {
		Statistic rank = Statistic.getRank(match,isBouns);
		assertThat(rank.getPrize()).isEqualTo(profit);
	}

	@Test
	void getMatchingNumberTest() {
		assertThat(Statistic.BONUS.getMatchingNumbers()).isEqualTo(5);
	}
}