package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbersTest {
    @DisplayName("shuffle 기능 테스트")
    @Test
    void shuffleLottoNumbersTest() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        LottoNumbers.shuffleLottoNumbers();
        boolean expected = lottoNumbers != LottoNumbers.getInstance();
        Assertions.assertThat(expected).isTrue();
    }
}
