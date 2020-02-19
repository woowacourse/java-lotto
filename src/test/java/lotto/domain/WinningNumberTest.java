package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumberTest {
	@Test
	public void initWinningNumberTest() {
		Lotto winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		BonusNumber bonusNumber = new BonusNumber(7);
		BonusNumber duplicatedBonusNumber = new BonusNumber(4);

		assertThat(new WinningNumber(winningNumber, bonusNumber)).isNotNull();

		assertThatThrownBy(() -> new WinningNumber(winningNumber, duplicatedBonusNumber))
			.isInstanceOf(LottoNumberDuplicatedException.class)
			.hasMessageContaining("중복");
	}
}