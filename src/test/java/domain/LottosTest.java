package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottosTest {
	@Test
	void 랭크_리스트_생성() {
		//given
		List<Lotto> lotto = new ArrayList<>(Arrays.asList(new Lotto(Arrays.asList(new LottoNumber("6"),
			new LottoNumber("5"),
			new LottoNumber("4"),
			new LottoNumber("3"),
			new LottoNumber("2"),
			new LottoNumber("1"))), new Lotto(Arrays.asList(new LottoNumber("11"),
			new LottoNumber("5"),
			new LottoNumber("4"),
			new LottoNumber("3"),
			new LottoNumber("2"),
			new LottoNumber("1")))));

		Lotto winningLotto = new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")));
		LottoNumber bonusNumber = new LottoNumber("7");
		Lottos lottos = new Lottos(lotto);
		//when
		List<Rank> ranks = lottos.calculateRank(new WinningLotto(winningLotto, bonusNumber));
		//then

		assertThat(ranks).contains(Rank.FIRST, Rank.THIRD);
	}
}
