package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@Test
	@DisplayName("입력한 금액이 1000원 단위가 아닐 때 예외를 발생시킨다")
	void checkUnitOfMoney() {
		assertThatThrownBy(() -> new Money(12345))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("구입 금액은 1000원 단위여야 합니다");
	}

	@Test
	@DisplayName("입력한 금액이 1000원보다 적을 때 예외를 발생시킨다")
	void checkMinimumMoney() {
		assertThatThrownBy(() -> new Money(0))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("최소 구입 금액은 1000원 입니다.");
	}
}
