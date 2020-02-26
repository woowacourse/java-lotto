package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class MoneyTest {
	@DisplayName("숫자가 아닌 구입금액을 입력한 경우")
	@Test
	void validateInteger() {
		assertThatThrownBy(() -> new Money("학성"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000원 미만의 금액을 입력한 경우")
	@Test
	void validateOverThousand() {
		assertThatThrownBy(() -> new Money("999"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000원으로 나누기")
	@Test
	void divideThousand() {
		Money money = new Money("14000");
		assertThat(money.findBuyAmount()).isEqualTo(14);
	}

	@DisplayName("수익률 계산")
	@Test
	void calculateIncomeRate() {
		Money money = new Money("1000");
		assertThat(money.calculateIncomeRate(3000)).isEqualTo(300);

		money = new Money("1000");
		assertThat(money.calculateIncomeRate(0)).isEqualTo(0);

		money = new Money("10000");
		assertThat(money.calculateIncomeRate(5000)).isEqualTo(50);
	}
}
