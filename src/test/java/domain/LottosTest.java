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
		LottoNumber bonusNumber = LottoNumber.of(7);
		Lottos lottos = new Lottos(lotto);
		//when
		Map<Rank,Long> ranks = lottos.countRank(new WinningLotto(winningLotto, bonusNumber));
		//then
		assertThat(ranks).containsAnyOf(entry(Rank.FIFTH, 1L), entry(Rank.THIRD, 1L));
	}

	@DisplayName("아무것도 맞지 않을 경우를 미포함 한 결과가 출력 되는지")
	@Test
	void nothing_count_rank_success() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"}),
			Lotto.of(new String[]{"8", "9", "10", "11", "12", "13"}));
		Lotto winningLotto = Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = LottoNumber.of(7);
		Lottos lottos = new Lottos(lotto);
		//when
		Map<Rank,Long> ranks = lottos.countRank(new WinningLotto(winningLotto, bonusNumber));
		//then
		assertThat(ranks).containsAnyOf(entry(Rank.FIRST, 1L));
	}
}
