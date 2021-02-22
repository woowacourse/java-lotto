package lotto.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoControllerTest {
	private Scanner scanner;

	private static Stream<Arguments> provideInputsForLottoTrial() {
		return Stream.of(
				Arguments.of("2000\n1\n1, 2, 3, 4, 5, 6\n1, 2, 3, 4, 5, 7\n10\n", true),
				Arguments.of("999\n1\n1, 2, 3, 4, 5, 6\n1, 2, 3, 4, 5, 7\n10\n", false), // money lower than 1000
				Arguments.of("2000\n0\n1, 2, 3, 4, 5, 7\n10\n", true),
				Arguments.of("2000\n1\n1, 2, 3, 3, 5, 6\n1, 2, 3, 4, 5, 7\n10\n", false), // duplicate number in manual lotto numbers input
				Arguments.of("2000\n0\n1, 2, 3, 4, 5, 7\n7\n", false) // winning numbers have duplicate number with bonus number
		);
	}

	@DisplayName("관련 인풋들이 주어질 때 제대로 동작하는지")
	@ParameterizedTest
	@MethodSource("provideInputsForLottoTrial")
	void tryLotto(String inputGroup, boolean result) {
		InputStream inputStream = generateUserInput(inputGroup);
		System.setIn(inputStream);
		scanner = new Scanner(System.in);

		assertThat(isControllerRunningSuccessfully(new LottoController()) == result).isTrue();
	}

	private InputStream generateUserInput(String input) {
		return new ByteArrayInputStream(input.getBytes());
	}

	private boolean isControllerRunningSuccessfully(LottoController lottoController) {
		try {
			lottoController.tryLotto(scanner);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@AfterEach
	void closeScanner() {
		scanner.close();
	}
}