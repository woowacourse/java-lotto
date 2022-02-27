package model.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import model.lotto.LottoCount;

public class RateOfReturnTest {
	@ParameterizedTest
	@EnumSource(Statistics.class)
	@DisplayName("결과가 저장이 잘 되는지 테스트")
	void saveResultTest(Statistics statistics) {
		RateOfReturn rateOfReturn = new RateOfReturn(new LottoCount("1000"));
		rateOfReturn.saveResult(statistics);
		assertThat(rateOfReturn.countStatistics(statistics)).isEqualTo(1);
	}

	@Test
	@DisplayName("수익률이 정상적으로 출력되는지 테스트")
	void getRateOfReturn() {
		RateOfReturn rateOfReturn = new RateOfReturn(new LottoCount("1000"));
		rateOfReturn.saveResult(Statistics.THREE);
		rateOfReturn.saveResult(Statistics.BONUS);
		assertThat(rateOfReturn.getRateOfReturn()).isEqualTo(
			(Statistics.THREE.getValue() + Statistics.BONUS.getValue()) / 1000);
	}
}
