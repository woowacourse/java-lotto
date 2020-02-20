package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {
	@Test
	void 생성_테스트() {
		assertThat(LottoFactory.create());

	}

	@ParameterizedTest
	@DisplayName("수동으로 생성된 로또를 비교할 수 있다.")
	@CsvSource(value = {"1,2,3,4,5,6:4", "4,7,44,42,33,13:2"}, delimiter = ':')
	void 로또_수동_생성_테스트(String input, int expected) {
		Lotto lotto = LottoFactory.create(input);
		List<Number> numbers = Arrays.asList(
			Number.of("3"),
			Number.of("4"),
			Number.of("5"),
			Number.of("6"),
			Number.of("7"),
			Number.of("45")
		);
		Lotto winningLotto = new Lotto(numbers);
		assertThat(lotto.compare(winningLotto)).isEqualTo(expected);
	}
}