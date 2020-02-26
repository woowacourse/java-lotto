package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.LottoTicket;

class WinningLottoTest {
	@Test
	void WinningLotto_ValidWinningLottoTicketAndBonusLottoNumber_GenerateInstance() {
		LottoTicket winningLottoTicket = LottoTicket.valueOf("4, 5, 6, 7, 8, 9");
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(10);

		assertThat(new WinningLotto(winningLottoTicket, bonusLottoNumber)).isInstanceOf(WinningLotto.class);
	}

	@Test
	void validateDuplication_DuplicateWinningLottoTicketAndBonusLottoNumber_InvalidWinningLottoExceptionThrown() {
		LottoTicket winningLottoTicket = LottoTicket.valueOf("4, 5, 6, 7, 8, 9");
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(9);

		assertThatThrownBy(() -> new WinningLotto(winningLottoTicket, bonusLottoNumber))
			.isInstanceOf(InvalidWinningLottoException.class)
			.hasMessage(InvalidWinningLottoException.DUPLICATION);
	}

	@Test
	void match_PurchasedLottoTicket_ReturnMatchLottoRank() {
		LottoTicket lottoTicket = LottoTicket.valueOf("1, 2, 3, 4, 5, 6");
		LottoTicket winningLottoTicket = LottoTicket.valueOf("2, 3, 4, 5, 6, 7");
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(1);
		WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusLottoNumber);

		LottoRank actual = winningLotto.match(lottoTicket);

		LottoRank expected = LottoRank.SECOND;
		assertThat(actual).isEqualTo(expected);
	}
}
