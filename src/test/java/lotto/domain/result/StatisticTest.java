package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StatisticTest {
	@Test
	@DisplayName("맞힌 수와 매칭되는 Enum을 반환")
	void getRankTest() throws Exception {
		assertThat(Statistic.getRank(3)).isEqualTo(Statistic.THREE);
		assertThat(Statistic.getRank(4)).isEqualTo(Statistic.FOUR);
	}

	@Test
	@DisplayName("카운트가 정상 동작 하는 지")
	void countTest() throws Exception {
		Statistic rank = Statistic.getRank(3);
		rank.count();
		assertThat(rank.getCount()).isEqualTo(1);
	}

	@ParameterizedTest
	@CsvSource(value = {"5,1500000", "4,50000"})
	@DisplayName("수익을 정상적으로 반환하는 지")
	void profitTest(int match, double profit) throws Exception {
		Statistic rank = Statistic.getRank(match);
		rank.count();
		assertThat(rank.getProfit()).isEqualTo(profit);
	}

	@Test
	void getMatchingNumberTest() {
		assertThat(Statistic.BONUS.getMatchingNumbers()).isEqualTo(5);
	}
}