package lotto.domain.lottos.generator;

import lotto.domain.lottos.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {

    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new RandomNumberGenerator().generateNumbers();
    }

    @Test
    @DisplayName("6개의 숫자들을 반환해준다.")
    public void createNumbersTest() {
        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("6개의 숫자 정렬해준다.")
    public void sortNumbersTest() {
        int number = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lottoNumber.getNumber() > number).isTrue();
            number = lottoNumber.getNumber();
        }
    }
}