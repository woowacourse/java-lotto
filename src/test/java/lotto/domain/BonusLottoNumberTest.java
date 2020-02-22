package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class BonusLottoNumberTest {
	private WinningLotto winningLotto;

	@BeforeEach
	void setUp() {
		winningLotto = new WinningLotto(
				LottoNumber.getCache().stream()
						.limit(6)
						.collect(Collectors.toList())
		);
	}


	@Test
	void BonusLottoNumber_올바른_동작_확인() {
		BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("7", winningLotto);
		assertThat(bonusLottoNumber).isInstanceOf(BonusLottoNumber.class);
	}

	@Test
	void BonusLottoNumber_중복_입력시_예외처리() {
		assertThatThrownBy(() -> {
			BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("6", winningLotto);
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("보너스 번호는 당첨번호와 중복될 수 없습니다.");
	}

	@Test
	void BonusLottoNumber_잘못된_입력시_예외처리() {
		assertThatThrownBy(() -> {
			BonusLottoNumber bonusLottoNumber = new BonusLottoNumber("a", winningLotto);
		}).isInstanceOf(InvalidLottoNumberException.class)
				.hasMessage("입력 보너스 번호가 정수가 아닙니다.");
	}
}
