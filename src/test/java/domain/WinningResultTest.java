package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

	@Test
	@DisplayName("1등 당첨 결과 확인")
	void checkFirstWinningResult() {
		// given
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(36);
		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.FIRST)).isEqualTo(1);
	}

	@Test
	@DisplayName("2등 당첨 결과 확인")
	void checkSecondWinningResult() {
		// given
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 34)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(35);
		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.SECOND)).isEqualTo(1);
	}

	@Test
	@DisplayName("3등 당첨 결과 확인")
	void checkThirdWinningResult() {
		// given
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 34)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(39);
		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.THIRD)).isEqualTo(1);
	}

	@Test
	@DisplayName("1000원으로 5등 당첨 시, 수익률 확인")
	void checkRateOfProfit() {
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 22, 34, 44)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(39);
		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));
		Money money = new Money(1000);

		// when
		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);

		//then
		assertThat(winningResult.getRateOfProfit(money)).isEqualTo(5.0);
	}

	@Test
	@DisplayName("1000원으로 1등 당첨 시, 수익률 확인")
	void checkRateOfProfitFirstWinning() {
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(39);
		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));
		Money money = new Money(1000);

		// when
		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);

		//then
		assertThat(winningResult.getRateOfProfit(money)).isEqualTo(2000000.0);
	}
}