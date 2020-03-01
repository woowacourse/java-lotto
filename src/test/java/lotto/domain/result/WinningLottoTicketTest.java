package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.ticket.LottoBall;
import lotto.domain.ticket.LottoTicket;

public class WinningLottoTicketTest {
	private LottoTicket lottoTicket;

	@BeforeEach
	void setup() {
		lottoTicket = LottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 6));
	}

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		LottoBall ball = LottoBall.valueOf(6);

		assertThatThrownBy(() -> new WinningLotto(lottoTicket, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("입력받은 로또로부터 당첨 등수 반환 확인")
	@Test
	public void calculateRankTest() {
		WinningLotto winningLotto = new WinningLotto(LottoTicket.of(Arrays.asList(1, 2, 3, 11, 12, 13)),
			LottoBall.valueOf(10));

		LottoRank rank = winningLotto.calculateRank(lottoTicket);
		assertThat(rank).isEqualTo(LottoRank.FIFTH);
	}
}
