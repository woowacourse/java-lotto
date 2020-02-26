package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 당첨 번호 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class WinningLottoTest {
	private Lotto lotto;

	@BeforeEach
	void setup() {
		lotto = Lotto.of(1, 2, 5, 43, 44, 45);
	}

	@Test
	@DisplayName("로또 한 장과 보너스 볼을 인자로 받아 인스턴스를 생성한다")
	void constructor() {
		assertThat(new WinningLotto(lotto, LottoNumber.of(6))).isInstanceOf(WinningLotto.class);
	}

	@Test
	@DisplayName("로또 한 장과 보너스 번호가 중복되는 경우 예외가 발생한다")
	void constructor_LottoNumberAndBonusNumberDuplicated() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
				() -> new WinningLotto(lotto, LottoNumber.of(45)));
	}

	@Test
	@DisplayName("로또를 한 장 받아 반환한 당첨 순위를 반환한다")
	void match() {
		WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.of(9));
		assertThat(winningLotto.match(Lotto.of(1, 2, 5, 43, 44, 45))).isEqualTo(LottoRank.FIRST);
	}
}
