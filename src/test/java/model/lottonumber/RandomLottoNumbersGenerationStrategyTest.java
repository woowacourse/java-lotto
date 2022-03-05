package model.lottonumber;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.lottonumber.generationstrategy.RandomLottoNumbersGenerationStrategy;

public class RandomLottoNumbersGenerationStrategyTest {

	@Test
	@DisplayName("랜덤 숫자 리스트 사이즈 검증")
	void validateLottoNumbersSize() {
		RandomLottoNumbersGenerationStrategy randomLottoNumbersGenerationStrategy = new RandomLottoNumbersGenerationStrategy();
		List<LottoNumber> lottoNumbers = randomLottoNumbersGenerationStrategy.generate(6);
		assertThat(lottoNumbers.size()).isEqualTo(6);
	}
}
