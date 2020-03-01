package lotto.domain.ticket;

import static java.util.Arrays.*;
import static lotto.domain.result.LottoRank.*;
import static lotto.domain.ticket.LottoBall.valueOf;
import static lotto.domain.ticket.LottoTicket.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.result.LottoRank;
import lotto.domain.result.LottoRanks;
import lotto.domain.result.WinningLotto;

public class LottoTicketsTest {
	@DisplayName("로또 묶음이 입력받은 로또들 가지고 있는지 테스트")
	@Test
	void makeLottos() {
		Set<LottoBall> balls = new HashSet<>(
			asList(valueOf(1), valueOf(2), valueOf(3), valueOf(4),
				valueOf(5), valueOf(6)));
		LottoTicket firstLottoTicket = new LottoTicket(balls);
		LottoTicket secondLottoTicket = new LottoTicket(balls);

		LottoTickets lottoTickets = new LottoTickets(asList(firstLottoTicket, secondLottoTicket));
		assertThat(lottoTickets.getLottoTickets()).containsExactly(firstLottoTicket, secondLottoTicket);
	}

	@DisplayName("가지고 있는 로또들을 당첨결과와 비교해 랭크 목록 반환 기능 확인")
	@ParameterizedTest
	@MethodSource("get_winningLotto_and_rankResults_test_set")
	void calculateRankResultsTest2(WinningLotto winningLotto, List<LottoRank> ranks) {
		LottoTickets lottos = new LottoTickets(asList(
			of(1, 2, 3, 4, 5, 6),
			of(1, 2, 3, 4, 5, 6),
			of(1, 2, 3, 4, 5, 7),
			of(1, 2, 3, 11, 12, 13),
			of(1, 2, 10, 11, 12, 13)
		));

		LottoRanks lottoRanks = lottos.matchLottoRanks(winningLotto);
		assertThat(lottoRanks).extracting("lottoRanks").isEqualTo(ranks);
	}

	private static Stream<Arguments> get_winningLotto_and_rankResults_test_set() {
		return Stream.of(
			Arguments.of(new WinningLotto(of(1, 2, 3, 4, 5, 6), valueOf(7)),
				Arrays.asList(FIRST, FIRST, SECOND, FIFTH, MISSING)),
			Arguments.of(new WinningLotto(of(1, 2, 3, 4, 5, 7), valueOf(8)),
				Arrays.asList(THIRD, THIRD, FIRST, FIFTH, MISSING)),
			Arguments.of(new WinningLotto(of(1, 2, 10, 11, 5, 6), valueOf(7)),
				Arrays.asList(FOURTH, FOURTH, FIFTH, FIFTH, FOURTH))
		);
	}
}
