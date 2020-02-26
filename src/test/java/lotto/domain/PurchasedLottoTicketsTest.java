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
}