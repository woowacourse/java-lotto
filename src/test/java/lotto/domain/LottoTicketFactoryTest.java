package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class LottoTicketFactoryTest {
	@Test
	void createLottoTicketWhenInputPurchasingAmount() {
		PurchasingAmount purchasingAmount = new PurchasingAmount(2000);
		List<LottoNumber> lottoNumbers1 = new ArrayList<>();
		List<LottoNumber> lottoNumbers2 = new ArrayList<>();
		List<LottoTicket> lottos = new ArrayList<>();

		lottoNumbers1.add(new LottoNumber(1));
		lottoNumbers1.add(new LottoNumber(22));
		lottoNumbers1.add(new LottoNumber(31));
		lottoNumbers1.add(new LottoNumber(4));
		lottoNumbers1.add(new LottoNumber(15));
		lottoNumbers1.add(new LottoNumber(6));

		lottoNumbers2.add(new LottoNumber(7));
		lottoNumbers2.add(new LottoNumber(8));
		lottoNumbers2.add(new LottoNumber(9));
		lottoNumbers2.add(new LottoNumber(12));
		lottoNumbers2.add(new LottoNumber(25));
		lottoNumbers2.add(new LottoNumber(36));

		LottoTicket lottoTicket1 = new LottoTicket(lottoNumbers1);
		LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers2);
		lottos.add(lottoTicket1);
		lottos.add(lottoTicket2);

		Map<Integer, List<Integer>> ticketNumbers = new HashMap<>();

		List<Integer> values1 = Arrays.asList(1, 22, 31, 4, 15, 6);
		List<Integer> values2 = Arrays.asList(7, 8, 9, 12, 25, 36);

		ticketNumbers.put(0, values1);
		ticketNumbers.put(1, values2);

		CreateSelectedNumbersStrategy createSelectedStrategy = new CreateSelectedNumbersStrategy(ticketNumbers);
		LottoTickets expected = new LottoTickets(lottos);
		LottoTickets actual = LottoTicketFactory.create(purchasingAmount, createSelectedStrategy);
		assertEquals(expected, actual);
	}
}
