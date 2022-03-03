package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import constant.LottoConstant;

class LottoNumbersGeneratorTest {

    @Test
    @DisplayName("랜덤 방식의 로또 숫자를 생성하는 기능")
    void createRandomLottoNumbers() {
        LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();

        List<LottoNumber> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers.size()).isEqualTo(LottoConstant.LOTTO_NUMBER_SIZE);
    }
}
