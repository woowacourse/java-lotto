package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.result.Rank;
import lotto.exception.InvalidWinningLottoException;

class WinningLottoTest {
	private WinningLotto winningLotto;

	@BeforeEach
	@DisplayName("당첨번호와 보너스 번호가 있는 로또 생성")
	void initWinningLotto() {
		List<Number> winningNumbers = Arrays.asList(
			LottoFactory.of("3"),
			LottoFactory.of("4"),
			LottoFactory.of("5"),
			LottoFactory.of("6"),
			LottoFactory.of("7"),
			LottoFactory.of("8")
		);
		Lotto winLotto = new Lotto(winningNumbers);
		Number bonusNumber = LottoFactory.of("45");
		this.winningLotto = new WinningLotto(winLotto, bonusNumber);
	}

	@Test
	@DisplayName("모든 값이 같은 로또는 1등을 반환하는 지")
	void rankTest() throws Exception {
		List<Number> winningNumbers = Arrays.asList(
			LottoFactory.of("3"),
			LottoFactory.of("4"),
			LottoFactory.of("5"),
			LottoFactory.of("6"),
			LottoFactory.of("7"),
			LottoFactory.of("8")
		);
		Lotto winLotto = new Lotto(winningNumbers);
		assertThat(winningLotto.isWinningLotto(winLotto)).isEqualTo(Rank.SIX);
	}

	@Test
	@DisplayName("5개와 보너스 넘버를 가진 로또는 2등을 반환하는 지")
	void secondTest() throws Exception {
		List<Number> winningNumbers = Arrays.asList(
			LottoFactory.of("3"),
			LottoFactory.of("4"),
			LottoFactory.of("5"),
			LottoFactory.of("6"),
			LottoFactory.of("7"),
			LottoFactory.of("45")
		);
		Lotto winLotto = new Lotto(winningNumbers);
		assertThat(winningLotto.isWinningLotto(winLotto)).isEqualTo(Rank.BONUS);
	}

	@Test
	@DisplayName("보너스번호와 당첨번호가 중복되는 경우 예외 발생")
	void bonusNumberDuplication() {
		List<Number> winningNumbers = Arrays.asList(
			LottoFactory.of("3"),
			LottoFactory.of("4"),
			LottoFactory.of("5"),
			LottoFactory.of("6"),
			LottoFactory.of("7"),
			LottoFactory.of("8")
		);
		assertThatThrownBy(() -> {
			new WinningLotto(new Lotto(winningNumbers), LottoFactory.of("3"));
		}).isInstanceOf(InvalidWinningLottoException.class)
			.hasMessageMatching("보너스 번호와 당첨번호는 중복될 수 없습니다.");
	}
}