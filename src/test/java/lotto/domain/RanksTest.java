package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RanksTest {
	@Test
	@DisplayName("ranks의 getCountOf메서드가 올바른 값을 계산한다")
	void checkGetCountOfWhenGivenSameRanks() {
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		LottoNumber lottoNumber1 = new LottoNumber(1);
		LottoNumber lottoNumber2 = new LottoNumber(2);
		LottoNumber lottoNumber3 = new LottoNumber(3);
		LottoNumber lottoNumber4 = new LottoNumber(4);
		LottoNumber lottoNumber5 = new LottoNumber(5);
		LottoNumber lottoNumber6 = new LottoNumber(6);
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		lottoNumbers.add(lottoNumber1);
		lottoNumbers.add(lottoNumber2);
		lottoNumbers.add(lottoNumber3);
		lottoNumbers.add(lottoNumber4);
		lottoNumbers.add(lottoNumber5);
		lottoNumbers.add(lottoNumber6);
		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		List<LottoTicket> lottoTicketList = new ArrayList<>();
		lottoTicketList.add(lottoTicket);
		LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
		Ranks ranks = new Ranks(lottoTickets, winningNumbers);
		assertThat(ranks.getCountOf(Rank.FIRST)).isEqualTo(1);
	}
}