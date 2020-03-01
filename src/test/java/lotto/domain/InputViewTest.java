package lotto.domain;

import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InputViewTest {
	@DisplayName("수동 로또의 개수가 0 일때")
	@Test
	void inputManualLottoNumbers() {
		Customer customer = new Customer(4000, 0);
		String manualLottoNumber = InputView.inputManualLottoNumbers(customer);

		assertThat(manualLottoNumber).isEqualTo("");
	}
}
