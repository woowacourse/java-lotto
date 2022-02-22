package model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomLottoNumbersGeneratorTest {

    @Test
    @DisplayName("랜덤 숫자 리스트 사이즈 검증")
    void validateLottoNumbersSize() {
         RandomLottoNumbersGenerator randomLottoNumbersGenerator = new RandomLottoNumbersGenerator();
         List<Integer> lottoNumbers = randomLottoNumbersGenerator.pickSixNumbers(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
         assertThat(lottoNumbers).isEqualTo(Arrays.asList(1,2,3,4,5,6));
    }
}
