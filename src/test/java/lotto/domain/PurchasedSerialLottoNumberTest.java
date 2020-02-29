package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestSerialLottoNumberFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class PurchasedSerialLottoNumberTest {
	@Test
	void PurchasedLottoTickets() {
		// given
		List<SerialLottoNumber> purchasedLottoTickets = new ArrayList<>();
		int[][] input = {{1, 10, 3, 11, 5, 6}, {5, 10, 45, 3, 17, 2}, {4, 7, 13, 19, 22, 37}};
		for (int[] element : input) {
			List<LottoNumber> lottoNumbers = Arrays.stream(element)
					.mapToObj(LottoNumber::new)
					.collect(Collectors.toList());
			SerialLottoNumber lottoTicket = new SerialLottoNumber(lottoNumbers);
			purchasedLottoTickets.add(lottoTicket);
		}

		// when
		PurchasedSerialLottoNumber result = new PurchasedSerialLottoNumber(purchasedLottoTickets);

		// then
		Assertions.assertThat(result.getPurchasedSerialLottoNumbers())
				.isEqualTo(purchasedLottoTickets);
	}

	@Test
	void of() {
		// when
		WinningLottoNumbers winningLottoNumbers
				= new WinningLottoNumbers(SerialLottoNumber.of("1,2,3,4,5,6"), new LottoNumber(7));

		PurchasedSerialLottoNumber purchasedSerialLottoNumber = PurchasedSerialLottoNumber.of(
				new PurchaseMoney(14000), new TestSerialLottoNumberFactory());

		LottoResult result = LottoResult.of(purchasedSerialLottoNumber, winningLottoNumbers);

		// then
		Map<WinningType, Integer> expected = new HashMap<>();
		for (WinningType key : WinningType.values()) {
			expected.put(key, 0);
		}
		expected.put(WinningType.FIRST_PLACE, 14);

		Assertions.assertThat(result.getLottoResult())
				.isEqualTo(expected);
	}

	@Test
	void add() {
		// given
		List<SerialLottoNumber> serialLottoNumbers = new ArrayList<>();
		serialLottoNumbers.add(SerialLottoNumber.of("1,2,3,4,5,6"));
		serialLottoNumbers.add(SerialLottoNumber.of("7,2,3,4,5,6"));
		serialLottoNumbers.add(SerialLottoNumber.of("1,7,3,4,5,6"));
		serialLottoNumbers.add(SerialLottoNumber.of("1,2,7,4,5,6"));

		List<SerialLottoNumber> serialLottoNumbers2 = new ArrayList<>();
		serialLottoNumbers2.add(SerialLottoNumber.of("1,2,3,4,7,6"));
		serialLottoNumbers2.add(SerialLottoNumber.of("1,2,3,4,5,7"));

		PurchasedSerialLottoNumber purchasedSerialLottoNumber
				= new PurchasedSerialLottoNumber(serialLottoNumbers);

		PurchasedSerialLottoNumber purchasedSerialLottoNumber2
				= new PurchasedSerialLottoNumber(serialLottoNumbers2);

		// when
		PurchasedSerialLottoNumber result
				= purchasedSerialLottoNumber.addAll(purchasedSerialLottoNumber2);

		// then
		List<SerialLottoNumber> serialLottoNumbers3 = new ArrayList<>();
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("7,2,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,7,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,7,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,7,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,5,7"));

		PurchasedSerialLottoNumber expected
				= new PurchasedSerialLottoNumber(serialLottoNumbers3);

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}