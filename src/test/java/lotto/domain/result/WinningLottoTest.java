package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.lotto.Lotto;
import lotto.domain.number.LottoNumber;

class WinningLottoTest {
	@DisplayName("WinningLotto 생성자 매개변수에 올바른 Lotto와 bonusNumber가 들어오면 정상적으로 WinningLotto 객체 생성")
	@Test
	void constructor_ValidLottoAndBonus_CreateWinningLotto() {
		WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");

		assertThat(winningLotto).isInstanceOf(WinningLotto.class);
	}

	@DisplayName("WinningLotto 생성자 매개변수에 서로 중복된 Lotto와 bonusNumber가 들어오면 InvalidWinningLottoException 발생")
	@Test
	void constructor_InvalidLottoAndBonus_ExceptionThrown() {
		assertThatThrownBy(() -> new WinningLotto("1,2,3,4,5,6", "6"))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.DUPLICATION);
	}
}
