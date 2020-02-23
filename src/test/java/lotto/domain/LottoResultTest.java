package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LottoResultTest {
	@Test
	void LottoResult() {
		// given
		Map<WinningType, Integer> map = new HashMap<>();
		map.put(WinningType.FIFTH_PLACE, 5);
		map.put(WinningType.NONE, 4);
		map.put(WinningType.FIRST_PLACE, 0);

		// when
		LottoResult result = new LottoResult(map);

		// then
		Assertions.assertThat(result.getLottoResult())
				.isEqualTo(map);
	}

	@Test
	void calculateEarningRate() {
		// given
		Map<WinningType, Integer> map = new HashMap<>();
		map.put(WinningType.FIRST_PLACE, 1);
		map.put(WinningType.SECOND_PLACE, 1);
		map.put(WinningType.THIRD_PLACE, 3);
		map.put(WinningType.FOURTH_PLACE,10);
		map.put(WinningType.FIFTH_PLACE,30);
		map.put(WinningType.NONE, 10000);
		LottoResult lottoResult = new LottoResult(map);

		// when
		int lottoNum = 1000 * (1 + 1 + 3 + 10 + 30 + 10000);
		PurchaseMoney purchaseMoney = new PurchaseMoney(lottoNum);
		double result =
			lottoResult.calculateEarningPercentage(new PurchaseMoney(lottoNum));

		// then
		int prizeMoney = WinningType.FIRST_PLACE.getWinningAmount()
				+ WinningType.SECOND_PLACE.getWinningAmount()
				+ WinningType.THIRD_PLACE.getWinningAmount() * 3
				+ WinningType.FOURTH_PLACE.getWinningAmount() * 10
				+ WinningType.FIFTH_PLACE.getWinningAmount() * 30;
		double expected = (double)prizeMoney / purchaseMoney.getPurchaseMoney() * 100;
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}
