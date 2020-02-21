package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

	@DisplayName("맞힌갯수와 등수 매치")
	@ParameterizedTest
	@CsvSource(value = {"0,NO_WIN", "3,FIFTH", "6,FIRST", "5,THIRD"})
	void findTest(int count, Rank values) {
		Rank lottoResult = Rank.findRank(count);
		assertThat(lottoResult == values).isTrue();
	}

}
