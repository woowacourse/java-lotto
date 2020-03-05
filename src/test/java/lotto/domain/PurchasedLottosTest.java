package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestLottoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class PurchasedLottosTest {
	@Test
	void PurchasedSerialLottoNumbers() {
		// given
		List<Lotto> purchasedSerialLottoNumbers = new ArrayList<>();
		int[][] input = {{1, 10, 3, 11, 5, 6}, {5, 10, 45, 3, 17, 2}, {4, 7, 13, 19, 22, 37}};
		for (int[] element : input) {
			List<Integer> lottoNumbers = Arrays.stream(element)
					.boxed()
					.collect(Collectors.toUnmodifiableList());
			Lotto serialLottoNumber = Lotto.of(lottoNumbers);
			purchasedSerialLottoNumbers.add(serialLottoNumber);
		}

		// when
		PurchasedLottos result = new PurchasedLottos(purchasedSerialLottoNumbers);

		// then
		Assertions.assertThat(result.getPurchasedLottos())
				.isEqualTo(purchasedSerialLottoNumbers);
	}

	@Test
	void of() {
		// when
		WinningLotto winningLotto
				= new WinningLotto(Lotto.of("1,2,3,4,5,6"), LottoNumber.of(7));

		PurchasedLottos purchasedLottos = PurchasedLottos.of(
				new LottoMoney(14000), new TestLottoFactory());

		LottoResult result = LottoResult.of(purchasedLottos, winningLotto);

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
		List<Lotto> serialLottoNumbers = new ArrayList<>();
		serialLottoNumbers.add(Lotto.of("1,2,3,4,5,6"));
		serialLottoNumbers.add(Lotto.of("7,2,3,4,5,6"));
		serialLottoNumbers.add(Lotto.of("1,7,3,4,5,6"));
		serialLottoNumbers.add(Lotto.of("1,2,7,4,5,6"));

		List<Lotto> serialLottoNumbers2 = new ArrayList<>();
		serialLottoNumbers2.add(Lotto.of("1,2,3,4,7,6"));
		serialLottoNumbers2.add(Lotto.of("1,2,3,4,5,7"));

		PurchasedLottos purchasedLottos
				= new PurchasedLottos(serialLottoNumbers);

		PurchasedLottos purchasedLottos2
				= new PurchasedLottos(serialLottoNumbers2);

		// when
		PurchasedLottos result
				= purchasedLottos.addAll(purchasedLottos2);

		// then
		List<Lotto> serialLottoNumbers3 = new ArrayList<>();
		serialLottoNumbers3.add(Lotto.of("1,2,3,4,5,6"));
		serialLottoNumbers3.add(Lotto.of("7,2,3,4,5,6"));
		serialLottoNumbers3.add(Lotto.of("1,7,3,4,5,6"));
		serialLottoNumbers3.add(Lotto.of("1,2,7,4,5,6"));
		serialLottoNumbers3.add(Lotto.of("1,2,3,4,7,6"));
		serialLottoNumbers3.add(Lotto.of("1,2,3,4,5,7"));

		PurchasedLottos expected
				= new PurchasedLottos(serialLottoNumbers3);

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}