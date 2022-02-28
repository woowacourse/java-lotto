package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

	static AnswerLotto answerLotto;

	@BeforeAll
	static void initAnswerLotto() {
		answerLotto = AnswerLotto.of(generateNumberList(1, 2, 3, 4, 5, 6), 7);
	}

	@Test
	void zeroMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(10, 11, 12, 13, 14, 15)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void oneMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(10, 11, 12, 13, 14, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void twoMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(10, 11, 12, 13, 5, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void threeMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(1, 2, 3, 7, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.THREE);
	}

	@Test
	void fourMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FOUR);
	}

	@Test
	void fiveMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE);
	}

	@Test
	void fiveAndBonusMatch() {
		LottoTicket lotto = new LottoTicket(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 7)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE_AND_BONUS);
	}

	@Test
	void sixMatch() {
		LottoTicket lottoTicket = new LottoTicket(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 6)));

		assertThat(lottoTicket.calculate(answerLotto)).isEqualTo(ResultStatics.SIX);
	}

}
