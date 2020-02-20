package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoResultTest {
	@DisplayName("맞힌갯수와 등수 매치")
	@ParameterizedTest
	@CsvSource(value = {"3,FIFTH", "6,FIRST", "5,THIRD"})
	void findTest(int count, LottoResult values) {
		LottoResult lottoResult = LottoResult.findRank(count);
		assertThat(lottoResult == values).isTrue();
	}

	@DisplayName("최종 수익 계산")
	@Test
	void calculateTotalReward() {
		LottoResult.FIFTH.countPlus();
		LottoResult.FOURTH.countPlus();
		assertThat(LottoResult.calculateTotalReward()).isEqualTo(55000);
	}
}
