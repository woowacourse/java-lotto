package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.exception.InvalidLottoPurchaseMoneyException;

public class LottoPurchaseMoneyTest {
	@Test
	@DisplayName("생성자 테스트")
	void constructor() {
		assertThat(new LottoPurchaseMoney("14000")).isExactlyInstanceOf(LottoPurchaseMoney.class);
	}

	@Test
	@DisplayName("구입 금액이 로또 가격보다 적은 경우")
	void constructor_구입_금액이_로또_가격보다_적은_경우() {
		assertThatExceptionOfType(InvalidLottoPurchaseMoneyException.class).isThrownBy(
				() -> new LottoPurchaseMoney("500")).withMessage("로또 한 장의 가격 1000원 보다 적습니다.");
	}

	@Test
	@DisplayName("숫자가 아닌 값을 입력 받은 경우")
	void constructor_숫자가_아닌_값을_입력_받은_경우() {
		assertThatExceptionOfType(NumberFormatException.class).isThrownBy(
				() -> new LottoPurchaseMoney("abc")).withMessage("숫자가 아닌 값을 입력하면 안됩니다.");
	}

	@Test
	@DisplayName("구입할 수 있는 개수 테스트")
	void getBuyCount() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney("14000");
		assertThat(lottoPurchaseMoney.getBuyCount()).isEqualTo(14);
	}
}
