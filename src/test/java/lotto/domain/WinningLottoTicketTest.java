package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.FixedLottoTicketsGenerator;
import lotto.domain.generator.LottoTicketsGenerator;

public class WinningLottoTicketTest {
	private LottoTicket lottoTicket;

	@BeforeEach
	void setup() {
		lottoTicket = LottoTicket.of(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		LottoBall ball = LottoBall.valueOf(6);

		assertThatThrownBy(() -> new WinningLotto(lottoTicket, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 당첨 내역 랭크 반환")
	@Test
	void getResult() {
		LottoBall ball = LottoBall.valueOf(40);
		WinningLotto winningLotto = new WinningLotto(lottoTicket, ball);
		LottoTicketGenerator lottoTicketGenerator = () -> lottoTicket;
		LottoCount count = LottoCount.valueOf(2);

		LottoTicketsGenerator generator = new FixedLottoTicketsGenerator(lottoTicketGenerator, count);
		LottoTickets lottoTickets = generator.create();

		WinningResult result = winningLotto.calculateResult(lottoTickets);
		Map<LottoRank, Long> lottoResult = result.getWinningResult();
		assertThat(lottoResult)
			.extractingByKeys(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST)
			.containsExactly(0L, 0L, 0L, 0L, 2L);
	}

	@DisplayName("입력받은 로또로부터 당첨 등수 반환 확인")
	@Test
	public void calculateRankTest() {
		WinningLotto winningLotto = new WinningLotto(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoBall.valueOf(10));
		LottoTicket lottoTicket = LottoTicket.of(1, 2, 3, 11, 12, 13);

		LottoRank rank = winningLotto.calculateRank(lottoTicket);
		assertThat(rank).isEqualTo(LottoRank.FIFTH);
	}
}
