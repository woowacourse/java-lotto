package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@DisplayName("구매 금액이 숫자 아니면 예외 발생")
	@Test
	void type_exception() {
		assertThatThrownBy(() -> {
			Money.from("2000원");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 구매 금액은 숫자로만 입력하세요");
	}

	@DisplayName("구매 금액이 0원 이하이면 예외 발생")
	@Test
	void boundary_exception() {
		assertThatThrownBy(() -> {
			Money.from("0");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 구매 금액은 0원보다 커야 합니다");
	}

	@DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
	@Test
	void unit_exception() {
		assertThatThrownBy(() -> {
			Money.from("1500");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력하세요");
	}

	@DisplayName("분모가 15000, 현재 보유 금액이 5000원일 경우 rate 는 3이다")
	@Test
	void rate_numerator_15000_amount_5000() {
		Money money = Money.from("5000");

		assertThat(money.rate(15000)).isEqualTo(3);
	}

	@DisplayName("분모가 1000, 현재 보유 금액이 2000원일 경우 rate 는 0.5이다")
	@Test
	void rate_numerator_1000_amount_2000() {
		Money money = Money.from("2000");

		assertThat(money.rate(1000)).isEqualTo(0.5);
	}

	@DisplayName("2000원치 로또가 2장인지 확인한다")
	@Test
	public void purchase_amount_2000() {
		Money money = Money.from("2000");

		assertThat(money.countAvailable(1000)).isEqualTo(2);
	}

	@DisplayName("3000원치 로또가 3장인지 확인한다")
	@Test
	public void purchase_amount_3000() {
		Money money = Money.from("3000");

		assertThat(money.countAvailable(1000)).isEqualTo(3);
	}
}
