package lotto.view;

import lotto.domain.LottoMachine;
import lotto.domain.LottoQuantity;
import lotto.domain.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static lotto.view.InputView.NON_NUMERIC_ERROR;
import static lotto.view.InputView.NOT_ENOUGH_MONEY_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class InputViewTest {
	private Scanner scanner;

	@AfterEach
	void closeScanner() {
		scanner.close();
	}

	@DisplayName("금액을 받을 때 검증을 잘 해주는지")
	@ParameterizedTest
	@MethodSource("provideInputsForMoneyInputTest")
	void takeMoneyInput(String moneyInput, String errorMessage) {
		InputStream inputStream = generateUserInput(moneyInput);
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThatThrownBy(() -> InputView.takeMoneyInput(scanner)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(errorMessage);
	}

	private static Stream<Arguments> provideInputsForMoneyInputTest() {
		return Stream.of(
				Arguments.of("999", NOT_ENOUGH_MONEY_ERROR),
				Arguments.of("정수가아니야", NON_NUMERIC_ERROR)
		);
	}

	@DisplayName("수동 로또 갯수를 입력받을 때 정수인지 확인")
	@Test
	void takeManualLottoQuantityInput() {
		InputStream inputStream = generateUserInput("정수아니야");
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThatThrownBy(() -> InputView.takeManualLottoQuantityInput(scanner)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NON_NUMERIC_ERROR);
	}

	@DisplayName("수동 로또 번호들을 입력 받을 때 정수인지 검증 해주는 지")
	@Test
	void takeManualLottoNumbersInput() {
		LottoMachine lottoMachine = new LottoMachine(new Money(2000), new LottoQuantity(2));

		InputStream inputStream = generateUserInput("1, 2, 3, 4, 5, 6\n1, 2, 3, 4, 5, 훼이크");
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThatThrownBy(() -> InputView.takeManualLottoNumbersInput(scanner, lottoMachine)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NON_NUMERIC_ERROR);
	}

	@DisplayName("당첨 번호를 입력받을 때 정수인지 검증")
	@Test
	void takeWinningNumbersInput() {
		InputStream inputStream = generateUserInput("1, 2, 3, 4, 5, 훼이크");
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThatThrownBy(() -> InputView.takeWinningNumbersInput(scanner)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NON_NUMERIC_ERROR);
	}

	@DisplayName("보너스 번호 입력받을 때 정수인지 검증")
	@Test
	void takeBonusNumberInput() {
		InputStream inputStream = generateUserInput("훼이크");
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThatThrownBy(() -> InputView.takeBonusNumberInput(scanner)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(NON_NUMERIC_ERROR);
	}

	private InputStream generateUserInput(String input) {
		return new ByteArrayInputStream(input.getBytes());
	}
}