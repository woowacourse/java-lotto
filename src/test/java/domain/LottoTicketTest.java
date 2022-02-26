package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

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
		WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningResult(winningNumbers));

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
		WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningResult(winningNumbers));

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
		Number bonusNumber = new Number(30);

		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningResult(winningNumbers));

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.THIRD)).isEqualTo(1);
	}

	@Test
	@DisplayName("4등 당첨 결과 확인")
	void checkFourthWinningResult() {
		// given
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 11, 15, 34)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(31);

		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningResult(winningNumbers));

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.FOURTH)).isEqualTo(1);
	}

	@Test
	@DisplayName("5등 당첨 결과 확인")
	void checkFifthWinningResult() {
		// given
		Lotto myLotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));
		Lotto winningLotto = new Lotto(Stream.of(1, 5, 9, 12, 15, 34)
			.map(Number::new)
			.collect(Collectors.toList()));
		Number bonusNumber = new Number(31);

		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
		LottoTicket lottoTicket = new LottoTicket(List.of(myLotto));

		// when
		WinningResult winningResult = new WinningResult(lottoTicket.checkLottoTicketWinningResult(winningNumbers));

		// then
		assertThat(winningResult.getWinningResult().get(LottoRank.FIFTH)).isEqualTo(1);
	}
}
