package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThatThrownBy;

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

	@DisplayName("구입가능한 로또 수 보다 큰 수의 수동로또를 구입한 경우")
	@Test
	void validateUserLottoCount() {
		assertThatThrownBy(() -> new Customer(3000, 4))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("구입 가능한 수보다 큰 수를 입력하였습니다.");
	}

	@DisplayName("수동 로또 번호가 한번만 set 되는지 확인")
	@Test
	void setManualLottoNumbers() {
		Customer customer = new Customer(4000, 1);
		customer.setManualLottoNumbers("1,2,3,4,5,6");
		customer.setManualLottoNumbers("11,12,13,14,15,16");

		assertThat(customer.getManualLottoNumbers())
				.isEqualTo(new String[]{"1,2,3,4,5,6"});
	}

	@DisplayName("수동로또 셋팅시 공란 입력")
	@Test
	void zeroManualLotto() {
		Customer customerZero = new Customer(5000, 0);
		customerZero.setManualLottoNumbers("");

		assertThat(customerZero.getManualLottoNumbers()).isEqualTo(new String[]{""});
	}
}
