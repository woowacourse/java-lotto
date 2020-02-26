package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.exception.NotEnoughMoneyException;

public class LottoStoreTest {
	@Test
	@DisplayName("구입금액만큼 로또 자동 구매 테스트")
	void buy_1() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("10000");
		assertThat(LottoStore.buy(lottoPurchaseMoney).size()).isEqualTo(10);
	}

	@Test
	@DisplayName("구입금액한도내 로또 수동 구매 테스트")
	void buy_2() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("10000");
		List<String> lottos = Arrays.asList(
				"1,2,3,4,5,6",
				"2,3,4,5,6,7",
				"3,4,5,6,7,8"
		);
		assertThat(LottoStore.buy(lottoPurchaseMoney, lottos).size()).isEqualTo(3);
	}

	@Test
	@DisplayName("구입금액을 초과하는 경우 예외 테스트")
	void buy_3() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("1000");
		List<String> lottos = Arrays.asList(
				"1,2,3,4,5,6",
				"2,3,4,5,6,7"
		);
		assertThatExceptionOfType(NotEnoughMoneyException.class).isThrownBy(
				() -> LottoStore.buy(lottoPurchaseMoney, lottos));
	}
}
