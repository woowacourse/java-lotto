package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

	private Lotto lotto = Lotto.of(new String[] {"1", "2", "3", "4", "5", "6"});

	@DisplayName("보너스 숫자가 로또 번호랑 중북되지 않는다")
	@Test
	void duplicate_success() {
		LottoNumber bonusNumber = new LottoNumber("7");
		assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
			.doesNotThrowAnyException();
	}

	@DisplayName("보너스 숫자가 로또 번호랑 중북된다")
	@Test
	void duplicate_fail() {
		LottoNumber bonusNumber = new LottoNumber("6");
		assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("등수 계산")
	@Test
	void calculate_rank() {
		//given
		WinningLotto winningLotto = new WinningLotto(Lotto.of(new String[] {"9", "5", "4", "3", "2", "1"}),
			new LottoNumber("6"));
		//when
		Rank rank = winningLotto.calculateRank(lotto);
		//then
		assertThat(rank).isEqualTo(Rank.SECOND);
	}
}
