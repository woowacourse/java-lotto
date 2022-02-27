package model.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import model.lotto.LottoCount;

public class RateOfReturnTest {
	@ParameterizedTest
	@EnumSource(Rank.class)
	@DisplayName("결과가 저장이 잘 되는지 테스트")
	void saveResultTest(Rank rank) {
		RateOfReturn rateOfReturn = new RateOfReturn(new LottoCount("1000"));
		rateOfReturn.saveResult(rank);
		assertThat(rateOfReturn.countStatistics(rank)).isEqualTo(1);
	}

	@Test
	@DisplayName("수익률이 정상적으로 출력되는지 테스트")
	void getRateOfReturn() {
		RateOfReturn rateOfReturn = new RateOfReturn(new LottoCount("1000"));
		rateOfReturn.saveResult(Rank.THREE);
		rateOfReturn.saveResult(Rank.BONUS);
		assertThat(rateOfReturn.getRateOfReturn()).isEqualTo(
			(Rank.THREE.getValue() + Rank.BONUS.getValue()) / 1000);
	}
}
