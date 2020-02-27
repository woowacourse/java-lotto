package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 로또 구입 금액 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoPurchaseMoneyTest {
	@Test
	@DisplayName("금액이 로또 구입 단위로 나누어 떨어지는 경우 인스턴스가 생성된다")
	void constructor() {
		assertThat(new LottoPurchaseMoney(14_000)).isExactlyInstanceOf(LottoPurchaseMoney.class);
	}

	@Test
	@DisplayName("생성자에서 구입 금액이 1000으로 나누어 떨어지지 않는 경우 예외가 발생한다")
	void constructor_MoneyDivideByUnit() {
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> new LottoPurchaseMoney(14_500));
	}

	@Test
	@DisplayName("가지고 있는 금액에 인자로 받은 값을 곱하여 반환한다")
	void multiply() {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(14_000);
		assertThat(lottoPurchaseMoney.multiply(100L)).isEqualTo(1_400_000L);
	}

	@ParameterizedTest
	@DisplayName("잔액이 있는지 여부를 반환한다")
	@CsvSource(value = {"1000,true", "0,false"})
	void hasBalance(long money, boolean expect) {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(money);
		assertThat(lottoPurchaseMoney.hasBalance()).isEqualTo(expect);
	}
}
