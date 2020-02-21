package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.exception.InvalidWinningLottoException;

public class WinningLottoTest {
	private Lotto lotto;

	@BeforeEach
	void setup() {
		lotto = LottoFactory.create("1,2,5,43,44,45");
	}

	@Test
	@DisplayName("당첨 로또가 정상적으로 생성되는지 확인")
	void constructor() {
		assertThat(new WinningLotto(lotto, LottoNumber.of(6))).isInstanceOf(WinningLotto.class);
	}

	@Test
	@DisplayName("로또 번호와 보너스가 겹칠 경우")
	@SuppressWarnings("NonAsciiCharacters")
	void constructor_로또_번호와_겹칠_경우() {
		assertThatExceptionOfType(InvalidWinningLottoException.class).isThrownBy(
				() -> new WinningLotto(lotto, LottoNumber.of(45))).withMessage("로또 번호와 보너스가 겹치면 안됩니다.");
	}

	@Test
	@DisplayName("로또 결과 확인")
	void match() {
		WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.of(9));
		assertThat(winningLotto.match(LottoFactory.create("1,2,5,43,44,45"))).isEqualTo(LottoRank.FIRST);
	}
}
