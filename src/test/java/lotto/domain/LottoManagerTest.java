package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LottoManagerTest {
	@Test
	public void calculateLottoAmountTest() {
		long amount = LottoManager.calculateLottoAmount(new Money(10_000));

		assertThat(amount).isEqualTo(10L);
	}
	@Test
	public void generateLottoByAmountTest() {
		int amount = 7;
		Lottos generatedLotto = LottoManager.
			generateLottoByAmount(amount, () -> Arrays.asList(1, 2, 3, 4, 5, 6));

		assertThat(generatedLotto.getLottos()).hasSize(amount);
	}
}