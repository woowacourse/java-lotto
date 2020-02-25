package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class PurchasedLottoTicketsTest {
	@Test
	void PurchasedLottoTickets() {
		// given
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();
		int[][] input = {{1, 10, 3, 11, 5, 6}, {5, 10, 45, 3, 17, 2}, {4, 7, 13, 19, 22, 37}};
		for (int[] element : input) {
			List<LottoNumber> lottoNumbers = Arrays.stream(element)
					.mapToObj(LottoNumber::getLottoNumber)
					.collect(Collectors.toList());
			SerialLottoNumber lottoTicket = new SerialLottoNumber(lottoNumbers);
			purchasedLottoTickets.add(lottoTicket);
		}

		// when
		PurchasedLottoTickets result = new PurchasedLottoTickets(purchasedLottoTickets);

		// then
		Assertions.assertThat(result.getPurchasedLottoTickets())
				.isEqualTo(purchasedLottoTickets);
	}

	@ParameterizedTest
	@ValueSource(ints = {1000, 10000, 50000})
	void of(int input) {
		// given
		MockLottoNumberGenerator lottoNumberGenerator = new MockLottoNumberGenerator();
		RandomLottoTicketFactory randomLottoTicketFactory = new RandomLottoTicketFactory(lottoNumberGenerator);

		// when
		PurchasedLottoTickets result
				= PurchasedLottoTicketsFactory.of(new PurchaseMoney(input), randomLottoTicketFactory);

		// then
		List<SerialLottoNumber> expected = new ArrayList<>();
		List<LottoNumber> lottoNumbers = lottoNumberGenerator.generateSixNumbers();

		for (int i = 0; i < input / 1000; i++) {
			expected.add(new SerialLottoNumber(lottoNumbers));
		}

		Assertions.assertThat(result.getPurchasedLottoTickets()).isEqualTo(expected);
	}
}