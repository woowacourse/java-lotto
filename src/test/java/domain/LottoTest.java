package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void correctNumbersInLotto() {
		List<Integer> userInput = List.of(1, 2, 3, 4, 5, 6);
		Lotto lotto = new Lotto(LottoGenerator.generateUserInputLottoNumbers(userInput));
		assertThat(lotto.getLottoNumbers()).contains(2, 3, 4, 5, 6, 1);
	}

	@Test
	void calculateThreeInAnswerNumbers() {
		List<Integer> userInput = List.of(1, 2, 3, 4, 5, 6);
		List<Integer> answerInput = List.of(1, 2, 3, 10, 11, 12);
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(LottoGenerator.generateUserInputLottoNumbers(userInput));
		assertThat(lotto.calculateInAnswerNumbers(new AnswerLotto(answerInput, bonusNumber))).isEqualTo(3);
	}

	@Test
	void hitBonus() {
		List<Integer> userInput = List.of(1, 2, 3, 4, 5, 45);
		List<Integer> answerInput = List.of(1, 2, 3, 10, 11, 12);
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(LottoGenerator.generateUserInputLottoNumbers(userInput));
		assertThat(lotto.isHitBonusNumber(new AnswerLotto(answerInput, bonusNumber))).isTrue();
	}

	@Test
	void doesNotHitBonus() {
		List<Integer> userInput = List.of(1, 2, 3, 4, 5, 6);
		List<Integer> answerInput = List.of(1, 2, 3, 10, 11, 12);
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(LottoGenerator.generateUserInputLottoNumbers(userInput));
		assertThat(lotto.isHitBonusNumber(new AnswerLotto(answerInput, bonusNumber))).isFalse();
	}
}
