package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
	@Test
	@DisplayName("수익률이 맞게 계산된다.")
	void checkCalculate() {
		Money money = new Money("1000000000");
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6","7");
		List<Rank> value = new ArrayList<>();
		value.add(Rank.FIFTH);
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
		Ranks ranks = new Ranks(lottoTickets,winningNumbers);

		assertThat(ProfitCalculator.calculate(money,ranks.getRanks())).isEqualTo(2);
	}
}