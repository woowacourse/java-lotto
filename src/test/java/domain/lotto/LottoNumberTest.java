package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    private LottoNumber lottoNumber;

    @ParameterizedTest
    @ValueSource(ints = {10, 1, 45})
    @DisplayName("생성 테스트")
    void test1(int value) {
        Assertions.assertThatCode(() -> new LottoNumber(value)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    @DisplayName("예외처리 테스트")
    void test2(int value) {
        Assertions.assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "2,0", "3,1", "4,1"})
    @DisplayName("비교 테스트")
    void test3(int value, int expected) {
        lottoNumber = new LottoNumber(value);
        LottoNumber comparedNumber = new LottoNumber(2);
        Assertions.assertThat(lottoNumber.compareTo(comparedNumber)).isEqualTo(expected);
    }
}
