package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

	@DisplayName("돈 받아서 1000원으로 떨어지는지 검증")
	@ParameterizedTest
	@ValueSource(ints = {1001, 999, 10010, -10, 0, -1000})
	void validDivideByThousand(int money) {
		assertThatThrownBy(() -> new Money(money))
			.isInstanceOf(IllegalArgumentException.class);
	}

	// @DisplayName("로또 개수 받아서 구입 금액을 돌려주는지 확인")
	// @Test
	// void getBuyMoney() {
	// 	assertThat(Money.getBuyMoney(new LottoCount(5))).isEqualTo(5000);
	// }

}
