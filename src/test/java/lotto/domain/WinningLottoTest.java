package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
	@DisplayName("WinningLotto 생성자에 당첨 번호와 보너스 번호 입력이 들어올 때 객체 생성")
	@Test
	void constructor_WinningLottoAndBonusNumber_CreateWinningLotto() {
		Lotto winningLotto = new Lotto(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)
		));
		LottoNumber bonusNumber = LottoNumber.valueOf(7);

		assertThat(new WinningLotto(winningLotto, bonusNumber))
			.isInstanceOf(WinningLotto.class);
	}

	@DisplayName("WinningLotto 생성자에 당첨 번호와 중복된 보너스 번호가 들어올 때 InvalidWinningLottoException 발생")
	@Test
	void validateDuplicated_WinningLottoAndDuplicatedBonusNumber_ExceptionThrown() {
		Lotto winningLotto = new Lotto(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)));
		LottoNumber bonusNumber = LottoNumber.valueOf(6);

		assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusNumber))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.DUPLICATED_BONUS_NUMBER);
	}
}
