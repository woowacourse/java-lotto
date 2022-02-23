package domain;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

	@Test
	void 보너스_중복_성공(){
		Lotto lotto = new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")));
		LottoNumber bonusNumber = new LottoNumber("7");
		assertThatCode(() -> new WinningLotto(lotto, bonusNumber))
			.doesNotThrowAnyException();
	}

	@Test
	void 보너스_중복_실패(){
		Lotto lotto = new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")));
		LottoNumber bonusNumber = new LottoNumber("6");
		assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 등수_계산() {
		//given
		Lotto lotto = new Lotto(Arrays.asList(new LottoNumber("7"),
				new LottoNumber("5"),
				new LottoNumber("4"),
				new LottoNumber("3"),
				new LottoNumber("2"),
				new LottoNumber("1")));
		WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(new LottoNumber("6"),
				new LottoNumber("5"),
				new LottoNumber("4"),
				new LottoNumber("3"),
				new LottoNumber("2"),
				new LottoNumber("1"))), new LottoNumber("7"));

		//when
		Rank rank = winningLotto.calculateRank(lotto);

		//then
		assertThat(rank).isEqualTo(Rank.SECOND);
	}
}
