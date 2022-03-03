package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @DisplayName("generateRandomLottoNumbers 메서드는 캐싱된 원본 LottoNumber 리스트에서 LottoNumber 사이즈가 6인 리스트를 추출한다.")
    @Test
    void generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumbersGenerator.generateRandomLottoNumbers();

        assertThat(lottoNumbers).hasSize(6);
    }

    @DisplayName("generateManualLottoNumbers 메서드는 입력받은 Integer 리스트를 LottoNumber 리스트로 변환한다.")
    @Test
    void generateManualLottoNumbers() {
        // given
        List<LottoNumber> lottoNumbers = LottoNumbersGenerator.generateManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        List<LottoNumber> result = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        // then
        assertThat(lottoNumbers).isEqualTo(result);
    }
}