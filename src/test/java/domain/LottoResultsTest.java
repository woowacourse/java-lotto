package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

public class LottoResultsTest {

    @DisplayName("로또 결과값 추가해서 잘 들어갔는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "15,15"})
    void 로또_결과값_추가_테스트(int size, int expected) {
        LottoResults lottoResults = new LottoResults();
        for(int i = 0 ; i < size ; i++) {
            lottoResults.add(new LottoResult(i, false));
        }

//        Assertions.assertThat(lottoResults.size()).isEqualTo(expected);
    }
}
