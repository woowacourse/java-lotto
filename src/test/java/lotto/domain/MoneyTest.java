package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
	@DisplayName("숫자가 아닌 구입금액을 입력한 경우")
	@Test
	void createTest() {
		assertThatThrownBy(() -> new Money("학성"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000원 미만의 금액을 입력한 경우")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> new Money("999"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000원으로 나누기")
	@Test
	void divideMoney() {
		Money money = new Money("14000");
		assertThat(money.divideThousand()).isEqualTo(14);
	}

}
