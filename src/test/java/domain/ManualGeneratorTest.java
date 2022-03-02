package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ManualGeneratorTest {
	@Test
	void generateAnsNumbersMoreThanFortyFive() {
		ManualGenerator manualGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 46));
		assertThatThrownBy(manualGenerator::generateLottoNumbers).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersLessThanOne() {
		ManualGenerator manualGenerator = new ManualGenerator(List.of(0, 2, 3, 4, 5, 6));
		assertThatThrownBy(manualGenerator::generateLottoNumbers).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeMoreThanSix() {
		ManualGenerator manualGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 6, 7));
		assertThatThrownBy(manualGenerator::generateLottoNumbers).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersSizeLessThanSix() {
		ManualGenerator manualGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5));
		assertThatThrownBy(manualGenerator::generateLottoNumbers).isInstanceOf(
			IllegalArgumentException.class);
	}

	@Test
	void generateAnsNumbersWithDuplicate() {
		ManualGenerator manualGenerator = new ManualGenerator(List.of(1, 2, 3, 3, 4, 5));
		assertThatThrownBy(manualGenerator::generateLottoNumbers).isInstanceOf(
			IllegalArgumentException.class);
	}
}
