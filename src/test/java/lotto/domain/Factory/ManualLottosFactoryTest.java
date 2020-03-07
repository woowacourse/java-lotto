package lotto.domain.Factory;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.exceptions.ManualLottosFactoryException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManualLottosFactoryTest {
	@Test
	void of() {
		// given
		String[] inputs = {"1,2,3,4,5,6", "7,8,9,10,11,12"};
		int money = 2000;

		// when
		ManualLottosFactory manualLottosFactory = ManualLottosFactory.of(
				LottoMoney.of(money), Arrays.asList(inputs));

		// then
		Assertions.assertThat(manualLottosFactory.create())
				.isEqualTo(Lottos.of(inputs));
	}

	@Test
	void of_ShouldThrowException() {
		// given
		String[] inputs = {"1,2,3,4,5,6", "7,8,9,10,11,12", "13,14,15,16,17,18"};
		int money = 2000;

		// then
		Assertions.assertThatThrownBy(() -> {
			ManualLottosFactory.of(LottoMoney.of(money), Arrays.asList(inputs));
		}).isInstanceOf(ManualLottosFactoryException.class);
	}
}