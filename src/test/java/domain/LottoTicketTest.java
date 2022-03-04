package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

	private static AnswerLotto answerLotto;

	@BeforeAll
	static void initAnswerLotto() {
		answerLotto = AnswerLotto.of(List.of(1, 2, 3, 4, 5, 6), 7);
	}

	@Test
	@DisplayName("일치하는 숫자가 존재하지 않는 경우 당첨되지 않음")
	void zeroMatch() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(10, 11, 12, 13, 14, 15)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	@DisplayName("하나의 숫자만 일치하는 경우 당첨되지 않음")
	void oneMatch() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(10, 11, 12, 13, 14, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	@DisplayName("두개의 숫자만 일치하는 경우 당첨되지 않음")
	void twoMatch() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(10, 11, 12, 13, 5, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	@DisplayName("3개의 숫자가 일치하고 보너스 숫자와는 일치하지 않는 경우 5등 당첨")
	void FIFTH_Match() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(1, 2, 3, 7, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIFTH);
	}

	@Test
	@DisplayName("3개의 숫자가 일치하고 보너스 숫자와도 일치하는 경우 4등 당첨")
	void FOURTH_Match() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(1, 2, 3, 4, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FOURTH);
	}

	@Test
	@DisplayName("4개의 숫자가 일치하는 경우 3등 당첨")
	void THIRD_Match() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(1, 2, 3, 4, 5, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.THIRD);
	}

	@Test
	@DisplayName("5개의 숫자가 일치하는 경우 2등 당첨")
	void SECOND_Match() {
		LottoTicket lotto = new LottoTicket(new LottoNumbers(List.of(1, 2, 3, 4, 5, 7)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.SECOND);
	}

	@Test
	@DisplayName("6개의 숫자가 일치하는 경우 1등 당첨")
	void FIRST_Match() {
		LottoTicket lottoTicket = new LottoTicket(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

		assertThat(lottoTicket.calculate(answerLotto)).isEqualTo(ResultStatics.FIRST);
	}

}
