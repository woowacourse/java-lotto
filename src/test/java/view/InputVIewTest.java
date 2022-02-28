package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

public class InputVIewTest {

	@Test
	void MoneyMustBeInteger() {
		String input = "우테코";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputMoney).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void MoneyMustNotBeEmpty() {
		String input = "\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputMoney).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void bonusNumberMustBeInteger() {
		String input = "-100";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputBonusNumber).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void AnswerNumberMustBeInteger() {
		String input = "-1,  , asd, ;]-, 5, 가나다";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputAnsNumbers).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void ManualLottoSizeMustBeInteger() {
		String input = "자동";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputManualLottoSize).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void ManualLottoSizeMustNotBeEmpty() {
		String input = "\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);
		assertThatThrownBy(InputView::inputManualLottoSize).isInstanceOf(IllegalArgumentException.class);
	}
}
