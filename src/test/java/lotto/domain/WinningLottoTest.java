package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class WinningLottoTest {
	private static Lotto lotto;

	@BeforeAll
	static void setUp() {
		Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
				LottoNumber.valueOf(1),
				LottoNumber.valueOf(2),
				LottoNumber.valueOf(3),
				LottoNumber.valueOf(4),
				LottoNumber.valueOf(5),
				LottoNumber.valueOf(6)
		));
		lotto = new Lotto(lottoNumbers);
	}

	@DisplayName("WinningLotto 생성자 매개변수에 올바른 Lotto와 bonusNumber가 들어오면 정상적으로 WinningLotto 객체 생성")
	@Test
	void constructor_validLottoAndBonus_createWinningLotto() {
		LottoNumber bonusNumber = LottoNumber.valueOf(10);

		WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

		assertThat(winningLotto).isInstanceOf(WinningLotto.class);
	}

	@DisplayName("WinningLotto 생성자 매개변수에 서로 중복된 Lotto와 bonusNumber가 들어오면 InvalidWinningLottoException 발생")
	@Test
	void constructor_InvalidLottoAndBonus_ExceptionThrown() {
		LottoNumber bonusNumber = LottoNumber.valueOf(1);
		assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
				.isInstanceOf(InvalidWinningLottoException.class)
				.hasMessage(InvalidWinningLottoException.DUPLICATION);
	}
}
