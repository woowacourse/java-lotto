package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.strategy.RandomLottoGeneratorStrategy;

class LottoNumbersGeneratorTest {

    @Test
    @DisplayName("1 ~ 45 사이의 LottoNumber 6개를 생성하는 기능")
    void createNumbers() {
        RandomLottoGeneratorStrategy lottoNumberGenerator = new RandomLottoGeneratorStrategy();

        List<LottoNumber> lottoNumbers = lottoNumberGenerator.generate();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}
