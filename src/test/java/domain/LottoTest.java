package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void correctNumbersInLotto() {
		ManualGenerator manualLottoGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 6));
		Lotto lotto = new Lotto(manualLottoGenerator.generateLottoNumbers());
		assertThat(lotto.getLottoNumbers()).contains(2, 3, 4, 5, 6, 1);
	}

	@Test
	void calculateThreeInAnswerNumbers() {
		ManualGenerator manualLottoGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 6));
		ManualGenerator manualAnswerGenerator = new ManualGenerator(List.of(1, 2, 3, 10, 11, 12));
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(manualLottoGenerator.generateLottoNumbers());
		assertThat(lotto.calculateInAnswerNumbers(
			new AnswerLotto(manualAnswerGenerator.generateLottoNumbers(), bonusNumber))).isEqualTo(3);
	}

	@Test
	void hitBonus() {
		ManualGenerator manualLottoGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 45));
		ManualGenerator manualAnswerGenerator = new ManualGenerator(List.of(1, 2, 3, 10, 11, 12));
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(manualLottoGenerator.generateLottoNumbers());
		assertThat(lotto.isHitBonusNumber(
			new AnswerLotto(manualAnswerGenerator.generateLottoNumbers(), bonusNumber))).isTrue();
	}

	@Test
	void doesNotHitBonus() {
		ManualGenerator manualLottoGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 6));
		ManualGenerator manualAnswerGenerator = new ManualGenerator(List.of(1, 2, 3, 10, 11, 12));
		LottoNumber bonusNumber = new LottoNumber(45);
		Lotto lotto = new Lotto(manualLottoGenerator.generateLottoNumbers());
		assertThat(lotto.isHitBonusNumber(
			new AnswerLotto(manualAnswerGenerator.generateLottoNumbers(), bonusNumber))).isFalse();
	}
}
