package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestSerialLottoNumberFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class PurchasedSerialLottoNumbersTest {
	@Test
	void PurchasedSerialLottoNumbers() {
		// given
		List<SerialLottoNumber> purchasedSerialLottoNumbers = new ArrayList<>();
		int[][] input = {{1, 10, 3, 11, 5, 6}, {5, 10, 45, 3, 17, 2}, {4, 7, 13, 19, 22, 37}};
		for (int[] element : input) {
			List<LottoNumber> lottoNumbers = Arrays.stream(element)
					.mapToObj(LottoNumber::of)
					.collect(Collectors.toList());
			SerialLottoNumber serialLottoNumber = new SerialLottoNumber(lottoNumbers);
			purchasedSerialLottoNumbers.add(serialLottoNumber);
		}

		// when
		PurchasedSerialLottoNumbers result = new PurchasedSerialLottoNumbers(purchasedSerialLottoNumbers);

		// then
		Assertions.assertThat(result.getPurchasedSerialLottoNumbers())
				.isEqualTo(purchasedSerialLottoNumbers);
	}

	@Test
	void of() {
		// when
		WinningLottoNumbers winningLottoNumbers
				= new WinningLottoNumbers(SerialLottoNumber.of("1,2,3,4,5,6"), LottoNumber.of(7));

		PurchasedSerialLottoNumbers purchasedSerialLottoNumbers = PurchasedSerialLottoNumbers.of(
				new PurchaseMoney(14000), new TestSerialLottoNumberFactory());

		LottoResult result = LottoResult.of(purchasedSerialLottoNumbers, winningLottoNumbers);

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

		PurchasedSerialLottoNumbers purchasedSerialLottoNumbers
				= new PurchasedSerialLottoNumbers(serialLottoNumbers);

		PurchasedSerialLottoNumbers purchasedSerialLottoNumbers2
				= new PurchasedSerialLottoNumbers(serialLottoNumbers2);

		// when
		PurchasedSerialLottoNumbers result
				= purchasedSerialLottoNumbers.addAll(purchasedSerialLottoNumbers2);

		// then
		List<SerialLottoNumber> serialLottoNumbers3 = new ArrayList<>();
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("7,2,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,7,3,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,7,4,5,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,7,6"));
		serialLottoNumbers3.add(SerialLottoNumber.of("1,2,3,4,5,7"));

		PurchasedSerialLottoNumbers expected
				= new PurchasedSerialLottoNumbers(serialLottoNumbers3);

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}