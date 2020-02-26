package lotto.domain.lottonumber;

import lotto.domain.lotto.WinningLotto;
import lotto.domain.lottonumber.BonusLottoNumber;
import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * BonusLottoNumber 테스트
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumberTest {
	private WinningLotto winningLotto;

	@BeforeEach
	void setUp() {
		winningLotto = new WinningLotto(
				LottoNumber.getLottoNumberCache().stream()
						.limit(6)
						.collect(Collectors.toList())
		);
	}

	@Test
	void BonusLottoNumber_올바른_동작_확인() {
		BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("7");
		assertThat(bonusLottoNumber).isInstanceOf(BonusLottoNumber.class);
	}

	@Test
	void BonusLottoNumber_중복_입력시_예외처리() {
		assertThatThrownBy(() -> {
			BonusLottoNumber.validateBonusLottoNumber("6", winningLotto);
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("보너스 번호는 당첨번호와 중복될 수 없습니다.");
	}

	@Test
	void BonusLottoNumber_문자_입력시_예외처리() {
		assertThatThrownBy(() -> {
			BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("a");
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("입력 보너스 번호가 정수가 아닙니다.");
	}

	@Test
	void BonusLottoNumber_범위_초과_입력시_예외처리() {
		assertThatThrownBy(() -> {
			BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("100");
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("유효한 로또 번호가 아닙니다.");
	}
}
