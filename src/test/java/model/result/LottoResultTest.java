package model.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LottoResultTest {
	@ParameterizedTest
	@EnumSource(Rank.class)
	@DisplayName("결과가 저장이 잘 되는지 테스트")
	void saveResultTest(Rank rank) {
		LottoResult lottoResult = new LottoResult();
		lottoResult.increaseCountOfRank(rank);
		assertThat(lottoResult.getCountOfResult(rank)).isEqualTo(1);
	}

	@Test
	@DisplayName("수익률이 정상적으로 출력되는지 테스트")
	void getRateOfReturn() {
		LottoResult lottoResult = new LottoResult();
		lottoResult.increaseCountOfRank(Rank.FIFTH);
		lottoResult.increaseCountOfRank(Rank.SECOND);
		assertThat(lottoResult.calculateSumOfRewards()).isEqualTo(
			(Rank.FIFTH.getValue() + Rank.SECOND.getValue()));
	}
}
