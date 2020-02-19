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
	@DisplayName("당첨 로또가 정상적으로 생성되는지 확인")
	void constructor() {
		assertThat(new WinningLotto(lotto, LottoNumber.of(6))).isInstanceOf(WinningLotto.class);
	}

	@Test
	@DisplayName("로또 번호와 보너스가 중복될 경우")
	void constructor_로또_번호와_보너스가_중복() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
				() -> new WinningLotto(lotto, LottoNumber.of(45)));
	}

	@Test
	@DisplayName("로또 결과 확인")
	void match() {
		WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.of(9));
		assertThat(winningLotto.match(Lotto.of(1, 2, 5, 43, 44, 45))).isEqualTo(LottoRank.FIRST);
	}
}
