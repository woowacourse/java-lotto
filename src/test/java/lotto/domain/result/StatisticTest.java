package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import lotto.exception.LottoMismatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StatisticTest {
	@Test
	@DisplayName("맞힌 수와 매칭되는 Enum을 반환")
	void getRankTest() throws LottoMismatchException {
		assertThat(Statistic.getRank(3,false)).isEqualTo(Statistic.THREE);
		assertThat(Statistic.getRank(4,false)).isEqualTo(Statistic.FOUR);
	}

	@Test
	@DisplayName("카운트가 정상 동작 하는 지")
	void countTest() throws Exception {
		Statistic rank = Statistic.getRank(3,false);
		rank.count();
		assertThat(rank.getCount()).isEqualTo(1);
	}

	@ParameterizedTest
	@CsvSource(value = {"5,false,1500000", "4,false,50000", "5,true,30000000"})
	@DisplayName("수익을 정상적으로 반환하는 지")
	void profitTest(int match, boolean bonusMatch, double profit) throws LottoMismatchException {
		Statistic rank = Statistic.getRank(match, bonusMatch);
		rank.count();
		assertThat(rank.getProfit()).isEqualTo(profit);
	}

	@Test
	void getMatchingNumberTest() {
		assertThat(Statistic.BONUS.getMatchingNumbers()).isEqualTo(5);
	}
}