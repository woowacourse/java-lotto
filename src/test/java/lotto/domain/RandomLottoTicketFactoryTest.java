package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomLottoTicketFactoryTest {
	@Test
	void createRandomLottoTicket() {
		// given
		MockLottoNumberGenerator randomLottoNumbersGenerator = new MockLottoNumberGenerator();

		// when
		SerialLottoNumber randomLottoTicket =
				new RandomLottoTicketFactory(randomLottoNumbersGenerator).create();

		// then
		List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toList());
		Assertions.assertThat(randomLottoTicket).isEqualTo(new SerialLottoNumber(lottoNumbers));
	}
}
