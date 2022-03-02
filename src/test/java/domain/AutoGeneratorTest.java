package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class AutoGeneratorTest {

	@Test
	void generateSixNumbers() {
		List<LottoNumber> generatedLottoNumbers = new AutoGenerator().generateLottoNumbers();
		assertThat(generatedLottoNumbers.size()).isEqualTo(6);
	}
}
