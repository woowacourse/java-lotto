package domain;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@DisplayName("로또 별 등수 계산 성공")
	@Test
	void count_rank_success() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"}),
			Lotto.of(new String[]{"11", "5", "4", "3", "2", "1"}));
		Lotto winningLotto = Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = new LottoNumber("7");
		Lottos lottos = new Lottos(lotto);
		//when
		Map<Rank,Integer> ranks = lottos.countRank(new WinningLotto(winningLotto, bonusNumber));
		//then
		assertThat(ranks).containsAnyOf(entry(Rank.FIFTH, 1), entry(Rank.THIRD, 1));
	}
}
