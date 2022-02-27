package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @DisplayName("generateLottoNumbers 메서드는 캐싱된 원본 LottoNumber 리스트에서 LottoNumber 사이즈가 6인 리스트를 추출한다.")
    @Test
    void generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumbersGenerator.generateLottoNumbers();

        assertThat(lottoNumbers).hasSize(6);
    }
}