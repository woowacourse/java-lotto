import static org.assertj.core.api.Assertions.assertThat;

import domain.AnswerLotto;
import domain.Lotto;

import java.util.ArrayList;
import java.util.Arrays;

import domain.ResultStatics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottoTest {

	static AnswerLotto answerLotto;

	@BeforeAll
	static void initAnswerLotto() {
		answerLotto = new AnswerLotto(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
	}

	@Test
	void numberListMustBeSix() {
		Lotto lotto = new Lotto(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.getNumbersSize()).isEqualTo(6);
	}

	@Test
	void zeroMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 14, 15)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void oneMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 14, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void twoMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(10, 11, 12, 13, 5, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void threeMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 7, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.THREE);
	}

	@Test
	void fourMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 8, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FOUR);
	}

	@Test
	void fiveMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 9)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE);
	}

	@Test
	void fiveAndBonusMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 7)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE_AND_BONUS);
	}

	@Test
	void sixMatch() {
		Lotto lotto = new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6)));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.SIX);
	}

}
