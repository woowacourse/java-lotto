package lotto.domain;

import lotto.domain.LottoTicketFactory.TestLottoTicketFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PurchasedLottoTicketsFactoryTest {

	@Test
	void create() {
		// when
		PurchasedLottoTickets result
				= PurchasedLottoTicketsFactory.create(new PurchaseMoney(14000), new TestLottoTicketFactory());

		// then
		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			serialLottoNumbers.add(SerialLottoNumber.of("1,2,3,4,5,6"));
		}
		PurchasedLottoTickets expected = new PurchasedLottoTickets(serialLottoNumbers);

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}