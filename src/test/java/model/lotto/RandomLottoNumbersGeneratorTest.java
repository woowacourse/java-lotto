package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.lottonumber.LottoNumber;
import model.lottonumber.RandomLottoNumbersGenerator;

public class RandomLottoNumbersGeneratorTest {

	@Test
	@DisplayName("랜덤 숫자 리스트 사이즈 검증")
	void validateLottoNumbersSize() {
		RandomLottoNumbersGenerator randomLottoNumbersGenerator = new RandomLottoNumbersGenerator();
		List<LottoNumber> lottoNumbers = randomLottoNumbersGenerator.pickLottoNumbers();
		assertThat(lottoNumbers.size()).isEqualTo(6);
	}
}
