package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.exception.NotEnoughMoneyException;

public class LottoBuyCountTest {
	@Test
	@DisplayName("금액이 부족한 경우 예외 테스트")
	void notEnoughMoney() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("1000");
		assertThatExceptionOfType(NotEnoughMoneyException.class).isThrownBy(
				() -> new LottoBuyCount(lottoPurchaseMoney, 2));
	}
}
