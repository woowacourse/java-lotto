package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestLottoFactory;
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
		map.put(WinningType.FOURTH_PLACE, 10);
		map.put(WinningType.FIFTH_PLACE, 30);
		map.put(WinningType.NONE, 10000);
		LottoResult lottoResult = new LottoResult(map);

		// when
		int lottoNum = 1000 * (1 + 1 + 3 + 10 + 30 + 10000);
		LottoMoney lottoMoney = new LottoMoney(lottoNum);
		double result =
				lottoResult.calculateEarningPercentage(new LottoMoney(lottoNum));

		// then
		int prizeMoney = WinningType.FIRST_PLACE.getWinningAmount()
				+ WinningType.SECOND_PLACE.getWinningAmount()
				+ WinningType.THIRD_PLACE.getWinningAmount() * 3
				+ WinningType.FOURTH_PLACE.getWinningAmount() * 10
				+ WinningType.FIFTH_PLACE.getWinningAmount() * 30;
		double expected = (double) prizeMoney / lottoMoney.getPurchaseMoney() * 100;
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}

	@Test
	void of() {
		// given
		PurchasedLottos purchasedLottos
				= PurchasedLottos.of(new LottoMoney(14000),
				new TestLottoFactory());
		Lotto serialLottoNumber = Lotto.of("1, 2, 3, 4, 5, 6");
		LottoNumber bonusNumber = LottoNumber.of(7);
		WinningLotto winningLotto
				= new WinningLotto(serialLottoNumber, bonusNumber);

		// when
		LottoResult result = LottoResult.of(purchasedLottos, winningLotto);

		// then
		Assertions.assertThat(result.getLottoResult().get(WinningType.FIRST_PLACE))
				.isEqualTo(14);
	}
}
