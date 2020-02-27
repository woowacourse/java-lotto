package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class CustomerTest {
	@DisplayName("1000원 미만의 금액을 입력한 경우")
	@Test
	void validateOverThousand() {
		assertThatThrownBy(() -> new Customer(999, 0))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("1000원으로 나누기")
	@Test
	void divideThousand() {
		Customer customer = new Customer(14000, 0);
		assertThat(customer.divideThousand()).isEqualTo(14);
	}

	@DisplayName("수익률 계산")
	@Test
	void calculateIncomeRate() {
		Customer customer = new Customer(1000, 0);
		assertThat(customer.calculateIncomeRate(3000)).isEqualTo(300);

		customer = new Customer(1000, 0);
		assertThat(customer.calculateIncomeRate(0)).isEqualTo(0);

		customer = new Customer(10000, 0);
		assertThat(customer.calculateIncomeRate(5000)).isEqualTo(50);
	}

	@DisplayName("구입가능한 로또수 보다 큰수의 수동로또는 구입한 경우")
	@Test
	void validateUserLottoCount() {
		assertThatThrownBy(() -> new Customer(3000, 4))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("구입 가능한 수보다 큰 수를 입력하였습니다.");
	}
}
