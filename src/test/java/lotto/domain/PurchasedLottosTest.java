package lotto.domain;

import lotto.domain.SerialLottoNumberFactory.TestLottoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class PurchasedLottosTest {
	@Test
	void of_Strings() {
		// given
		List<String> input = Arrays.asList("1, 10, 3, 11, 5, 6", "5, 10, 45, 3, 17, 2", "4, 7, 13, 19, 22, 37");

		// when
		PurchasedLottos result = PurchasedLottos.of(input);

		// then
		List<Lotto> expected = input.stream()
				.map(Lotto::of)
				.collect(Collectors.toUnmodifiableList());

		Assertions.assertThat(result.getPurchasedLottos())
				.isEqualTo(expected);
	}

	@Test
	void of_PurchasedLottosAndWinningLotto() {
		// when
		WinningLotto winningLotto
				= new WinningLotto(Lotto.of("1,2,3,4,5,6"), LottoNumber.of(7));

		PurchasedLottos purchasedLottos = PurchasedLottos.of(
				LottoMoney.of(14000), new TestLottoFactory());

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
		PurchasedLottos purchasedLottos
				= PurchasedLottos.of(Arrays.asList(
				"1,2,3,4,5,6",
				"7,2,3,4,5,6",
				"1,7,3,4,5,6",
				"1,2,7,4,5,6"));

		PurchasedLottos purchasedLottos2
				= PurchasedLottos.of(Arrays.asList(
				"1,2,3,4,7,6",
				"1,2,3,4,5,7"));

		// when
		PurchasedLottos result
				= purchasedLottos.addAll(purchasedLottos2);

		// then
		PurchasedLottos expected
				= PurchasedLottos.of(Arrays.asList(
				"1,2,3,4,5,6",
				"7,2,3,4,5,6",
				"1,7,3,4,5,6",
				"1,2,7,4,5,6",
				"1,2,3,4,7,6",
				"1,2,3,4,5,7"));

		Assertions.assertThat(result)
				.isEqualTo(expected);
	}
}