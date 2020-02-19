package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPurchaseMoneyTest {
	@Test
	@DisplayName("생성자 테스트")
	void constructor() {
		assertThat(new LottoPurchaseMoney(14000)).isExactlyInstanceOf(LottoPurchaseMoney.class);
	}

	@Test
	@DisplayName("생성자에서 구입 금액이 1000으로 나누어 떨어지지 않는 경우")
	void constructor_구입_금액이_1000_단위로_나누어_떨어지지_않는_경우() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new LottoPurchaseMoney(14500));
	}

	@Test
	@DisplayName("구입할 수 있는 개수 테스트")
	void getBuyCount() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(14000);
		assertThat(lottoPurchaseMoney.getBuyCount()).isEqualTo(14);
	}
}
