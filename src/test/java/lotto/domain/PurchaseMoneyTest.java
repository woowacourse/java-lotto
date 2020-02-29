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
public class PurchaseMoneyTest {
	@Test
	@DisplayName("금액이 로또 구입 단위로 나누어 떨어지는 경우 인스턴스가 생성된다")
	void constructor() {
		assertThat(new PurchaseMoney(14_000)).isExactlyInstanceOf(PurchaseMoney.class);
	}

	@Test
	@DisplayName("생성하려는 로또 구입 금액이 음수인 경우 예외가 발생한다")
	void constructor_MoneyDivideByUnit() {
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> new PurchaseMoney(-1_000));
	}

	@Test
	@DisplayName("가지고 있는 금액에 인자로 받은 값을 곱하여 반환한다")
	void multiply() {
		PurchaseMoney purchaseMoney = new PurchaseMoney(14_000);
		assertThat(purchaseMoney.multiply(100L)).isEqualTo(1_400_000L);
	}

	@ParameterizedTest
	@DisplayName("금액을 지불할 수 있는지 여부를 반환한다")
	@CsvSource(value = {"1000,true", "0,false"})
	void canPayable(long money, boolean expect) {
		PurchaseMoney purchaseMoney = new PurchaseMoney(money);
		assertThat(purchaseMoney.canPayable(1_000L)).isEqualTo(expect);
	}

	@Test
	@DisplayName("지불할 수 있는 금액을 지불하라고 요구하는 경우에는 가지고 있는 금액에서 차감한다")
	void pay() {
		PurchaseMoney purchaseMoney = new PurchaseMoney(10_000L);
		purchaseMoney.pay(1_000L);
		assertThat(purchaseMoney.get()).isEqualTo(9_000L);
	}

	@Test
	@DisplayName("지불할 수 없는 금액을 지불하라고 요구하는 경우에는 예외가 발생한다")
	void pay_NotPayableMoney() {
		PurchaseMoney purchaseMoney = new PurchaseMoney(10_000L);
		assertThatThrownBy(() -> purchaseMoney.pay(11_000L))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
