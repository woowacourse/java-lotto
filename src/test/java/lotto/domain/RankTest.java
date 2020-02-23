package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

	@DisplayName("맞힌갯수와 등수 매치")
	@ParameterizedTest
	@CsvSource(value = {"0,false,NO_WIN", "5,false,THIRD", "5,true,SECOND"})
	void findTest(int count, boolean isMatchBonus, Rank values) {
		Rank lottoResult = Rank.findRank(count, isMatchBonus);
		assertThat(lottoResult == values).isTrue();
	}

}
