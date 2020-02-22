package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerialLottoNumberFactoryTest {
	@Test
	void SerialLottoNumberFactory() {
		// given
		String input = "1, 45, 3, 4, 5, 6";

		// when
		SerialLottoNumber result = SerialLottoNumberFactory.create(input);

		//then
		SerialLottoNumber expected = new SerialLottoNumber(Stream.of(1, 3, 4, 5, 6, 45)
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList()));
		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}
