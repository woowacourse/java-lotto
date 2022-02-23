import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoGenerator;
import domain.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

	@Test
	void generateSixNumbers() {
		List<LottoNumber> generatedLottoNumbers = LottoGenerator.generateRandomLottoNumbers();
		assertThat(generatedLottoNumbers.size()).isEqualTo(6);
	}

	@Test
	void generateAnsNumbersMoreThanFortyFive() {
		String[] sampleInput = {"1", "2", "3", "4", "5", "46"};
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersLessThanOne() {
		String[] sampleInput = {"0", "2", "3", "4", "5", "6"};
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeMoreThanSix() {
		String[] sampleInput = {"1", "2", "3", "4", "5", "6", "7"};
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeLessThanSix() {
		String[] sampleInput = {"1", "2", "3", "4", "5"};
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}
}
