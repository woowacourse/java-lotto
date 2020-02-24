package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerialLottoNumberFactoryTest {
	@Test
	void createRandomLottoTicket() {
		// given
		MockLottoNumberGenerator randomLottoNumbersGenerator = new MockLottoNumberGenerator();

		// when
		SerialLottoNumber randomLottoTicket =
				SerialLottoNumberFactory.createRandomLottoTicket(randomLottoNumbersGenerator);

		// then
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(AllLottoNumbers::getLottoNumber)
				.collect(Collectors.toList());
		Assertions.assertThat(randomLottoTicket).isEqualTo(new SerialLottoNumber(lottoNumbers));
	}

	@Test
	void createWinningLottoNumbers() {
		// given
		String input = "1, 2, 3, 4, 5, 6";

		// when
		SerialLottoNumber winningLottoNumbers = SerialLottoNumberFactory.createWinningLottoNumbers(input);

		// then
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(AllLottoNumbers::getLottoNumber)
				.collect(Collectors.toList());
		Assertions.assertThat(winningLottoNumbers).isEqualTo(new SerialLottoNumber(lottoNumbers));
	}
}
