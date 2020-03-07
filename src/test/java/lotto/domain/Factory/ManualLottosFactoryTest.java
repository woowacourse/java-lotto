package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.exceptions.ManualLottosFactoryException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ManualLottosFactoryTest {
	@Test
	void of() {
		// given
		List<String> inputs = Arrays.asList("1,2,3,4,5,6", "7,8,9,10,11,12");
		int money = 2000;

		// when
		ManualLottosFactory manualLottosFactory = ManualLottosFactory.of(
				LottoMoney.of(money), inputs);

		// then
		Assertions.assertThat(manualLottosFactory.create())
				.isEqualTo(Lottos.of(inputs.stream()
						.map(Lotto::of)
						.collect(Collectors.toUnmodifiableList())));
	}

	@Test
	void of_ShouldThrowException() {
		// given
		List<String> inputs = Arrays.asList("1,2,3,4,5,6", "7,8,9,10,11,12", "13,14,15,16,17,18");
		int money = 2000;

		// then
		Assertions.assertThatThrownBy(() -> {
			ManualLottosFactory.of(LottoMoney.of(money), inputs);
		}).isInstanceOf(ManualLottosFactoryException.class);
	}
}