package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoGenerator;
import domain.LottoNumber;
import java.util.ArrayList;
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
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(1);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		sampleInput.add(46);
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersLessThanOne() {
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(0);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		sampleInput.add(6);
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeMoreThanSix() {
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(1);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		sampleInput.add(6);
		sampleInput.add(7);
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeLessThanSix() {
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(1);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersWithDuplicate() {
		List<Integer> sampleInput = new ArrayList<>();
		sampleInput.add(1);
		sampleInput.add(2);
		sampleInput.add(3);
		sampleInput.add(3);
		sampleInput.add(4);
		sampleInput.add(5);
		assertThatThrownBy(() -> LottoGenerator.generateAnswerLottoNumbers(sampleInput)).isInstanceOf(
			IllegalArgumentException.class);
	}
}
