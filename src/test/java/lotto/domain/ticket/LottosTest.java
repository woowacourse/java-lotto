package lotto.domain.ticket;

import lotto.domain.result.Rank;
import lotto.domain.result.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
	@DisplayName("가지고 있는 로또와 당첨번호를 비교해서 결과를 제대로 반환 하는지")
	@Test
	void getResults() {
		Lotto lotto1 = new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(3),
				new LottoNumber(19),
				new LottoNumber(20),
				new LottoNumber(40)));


		Lotto lotto2 = new Lotto(Arrays.asList(
				new LottoNumber(1),
				new LottoNumber(2),
				new LottoNumber(20),
				new LottoNumber(25),
				new LottoNumber(29),
				new LottoNumber(45)));

		Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

		WinningLotto winningLotto = new WinningLotto(
				new Lotto(Arrays.asList(
						new LottoNumber(1),
						new LottoNumber(2),
						new LottoNumber(3),
						new LottoNumber(4),
						new LottoNumber(5),
						new LottoNumber(6))),
				new LottoNumber(20));

		List<Rank> ranks = lottos.getResultsBasedOn(winningLotto);

		List<Rank> expectedRanks = Arrays.asList(Rank.FIFTH, Rank.NONE);

		assertThat(ranks).isEqualTo(expectedRanks);
	}
}
