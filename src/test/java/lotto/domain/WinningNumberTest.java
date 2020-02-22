package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.exceptions.LottoNumberDuplicatedException;

public class WinningNumberTest {
	@Test
	public void initWinningNumberTest() {
		Lotto winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber bonusNumber = new LottoNumber(7);

		assertThat(new WinningNumber(winningNumber, bonusNumber)).isNotNull();
	}

	@Test
	@DisplayName("보너스번호와 중복이있는 당첨번호 테스트")
	public void initWrongWinningNumberTest() {
		Lotto winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		LottoNumber duplicatedBonusNumber = new LottoNumber(4);

		assertThatThrownBy(() -> new WinningNumber(winningNumber, duplicatedBonusNumber))
			.isInstanceOf(LottoNumberDuplicatedException.class)
			.hasMessageContaining("중복");
	}
}