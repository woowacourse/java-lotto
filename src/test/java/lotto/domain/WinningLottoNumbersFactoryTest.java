package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WinningLottoNumbersFactoryTest {
	@Test
	void createWinningLottoNumbers() {
		// given
		String input = "1, 2, 3, 4, 5, 6";

		// when
		SerialLottoNumber winningLottoNumbers = WinningLottoNumbersFactory.createWinningLottoNumbers(input);

		// then
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toList());
		Assertions.assertThat(winningLottoNumbers).isEqualTo(new SerialLottoNumber(lottoNumbers));
	}
}