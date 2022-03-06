package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * - **수동으로 구매할 갯수를 입력받는다.** (추가)
 *    - [ 예외 ] 음수가 아니어야한다.
 *    - [ 예외 ] 문자가 아니어야한다. (paseInt())
 *    - [ 예외 ] 구입 금액 / 1000원을 초과하면 안된다.
 *    - [ 예외 ] 문자가 아니어야 한다.  (paseInt())
 *    - [ 예외 ] 빈 문자열이 아니어야 한다. (paseInt())
 */
public class OrderFormTest {

	@DisplayName("음수인 경우 실패")
	@Test
	void input_negative() {
		Payment payment = new Payment(5000);
		assertThatThrownBy(() -> new OrderForm(payment, -1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("음수는 불가능합니다.");
	}

	@DisplayName("수동 로또 갯수가 구매 가능한 갯수를 초과할 경우")
	@Test
	void buy_max_value() {
		//given
		Payment payment = new Payment(5000);
		//when
		int manualCount = 6;
		//then
		assertThatThrownBy(() -> new OrderForm(payment, manualCount))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("구매 가능한 갯수를 초과했습니다.");
	}

	@DisplayName("자동 로또 갯수 계산")
	@Test
	void auto_Lotto_size() {
		//given
		Payment payment = new Payment(10_000);
		OrderForm orderForm = new OrderForm(payment, 7);
		//when
		int autoCount = orderForm.calculateAutoLottoCount();
		//then
		assertThat(autoCount).isEqualTo(3);
	}
}