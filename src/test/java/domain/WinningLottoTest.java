package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

	private Lotto lotto = Lotto.of(new String[] {"1", "2", "3", "4", "5", "6"});

	@Test
	void 보너스_중복_성공() {
		LottoNumber bonusNumber = new LottoNumber("7");
		assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
			.doesNotThrowAnyException();
	}

	@Test
	void 보너스_중복_실패() {
		LottoNumber bonusNumber = new LottoNumber("6");
		assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 등수_계산() {
		//given
		WinningLotto winningLotto = new WinningLotto(Lotto.of(new String[]{"9", "5", "4", "3", "2", "1"}),
			new LottoNumber("6"));

		//when
		Rank rank = winningLotto.calculateRank(lotto);

		//then
		assertThat(rank).isEqualTo(Rank.SECOND);
	}
}
