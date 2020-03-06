package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

	@Test
	void calculateEarningPercentage() {
		// given
		Lottos lottos = Lottos.of("1,2,3,4,5,6",
				"1,2,3,4,5,7",
				"8,9,10,11,12,13");
		WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 7);
		LottoResult lottoResult = LottoResult.of(lottos, winningLotto);

		// when
		double result = lottoResult.calculateEarningPercentage(LottoMoney.of(3000));

		// then
		double expected = (WinningType.FIRST_PLACE.getWinningAmount() +
				WinningType.SECOND_PLACE.getWinningAmount()) / 3000d * 100;
		Assertions.assertThat(result)
				.isEqualTo(expected);

	}
}