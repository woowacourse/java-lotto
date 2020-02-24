package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 여러 개의 로또를 테스트하는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoTicketTest {
	@Test
	@DisplayName("우승 로또와 비교하여 통계를 정확히 만들어내는지 확인")
	void matchAll() {
		LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
				Lotto.of(1, 2, 5, 43, 44, 45),
				Lotto.of(1, 2, 5, 43, 44, 45),
				Lotto.of(1, 2, 3, 43, 44, 45)));
		WinningLotto winningLotto = new WinningLotto(Lotto.of(1, 2, 5, 43, 44, 45), LottoNumber.of(7));

		Map<LottoRank, Long> matchResult = new HashMap<>();
		matchResult.put(LottoRank.FIRST, 2L);
		matchResult.put(LottoRank.THIRD, 1L);
		MatchResult expected = new MatchResult(matchResult);

		assertThat(lottoTicket.matchAll(winningLotto)).isEqualTo(expected);
	}
}
