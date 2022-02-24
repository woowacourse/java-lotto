import domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

	static AnswerLotto answerLotto;

	@BeforeAll
	static void initAnswerLotto() {
		answerLotto = new AnswerLotto(new AnswerLottoNumbers(generateNumberList(1, 2, 3, 4, 5, 6)),
			new BonusNumber(7));
	}

	@Test
	void numberListMustBeSix() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 4, 5, 6));
		assertThat(lotto.getNumbersSize()).isEqualTo(6);
	}

	@Test
	void zeroMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(10, 11, 12, 13, 14, 15));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void oneMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(10, 11, 12, 13, 14, 6));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void twoMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(10, 11, 12, 13, 5, 6));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.NOTHING);
	}

	@Test
	void threeMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 7, 8, 9));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.THREE);
	}

	@Test
	void fourMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 4, 8, 9));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FOUR);
	}

	@Test
	void fiveMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 4, 5, 9));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE);
	}

	@Test
	void fiveAndBonusMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 4, 5, 7));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.FIVE_AND_BONUS);
	}

	@Test
	void sixMatch() {
		Lotto lotto = new Lotto(() -> generateNumberList(1, 2, 3, 4, 5, 6));

		assertThat(lotto.calculate(answerLotto)).isEqualTo(ResultStatics.SIX);
	}

	static List<Integer> generateNumberList(int n1, int n2, int n3, int n4, int n5, int n6) {
		return Arrays.asList(n1, n2, n3, n4, n5, n6);
	}
}
