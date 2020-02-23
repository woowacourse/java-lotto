package lotto.domain;

import lotto.domain.LottoTicketFactory.TestLottoTicketFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultFactoryTest {

	@Test
	void create() {
		// when
		WinningLottoNumbers winningLottoNumbers
				= new WinningLottoNumbers(SerialLottoNumber.of("1,2,3,4,5,6"), new LottoNumber(7));

		PurchasedLottoTickets purchasedLottoTickets = PurchasedLottoTicketsFactory.create(
				new PurchaseMoney(14000), new TestLottoTicketFactory());

		LottoResult result = LottoResultFactory.create(purchasedLottoTickets, winningLottoNumbers);

		// then
		Map<WinningType, Integer> expected = new HashMap<>();
		for (WinningType key : WinningType.values()) {
			expected.put(key, 0);
		}
		expected.put(WinningType.FIRST_PLACE, 14);

		Assertions.assertThat(result.getLottoResult())
				.isEqualTo(expected);
	}
}