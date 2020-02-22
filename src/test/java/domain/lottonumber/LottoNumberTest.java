package domain.lottonumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또 번호 테스트")
public class LottoNumberTest {
    @Test
    @DisplayName("로또 번호 범위 확인")
    void lottoNumberConstructorTest() {
        assertThatThrownBy(() -> LottoNumber.valueOf(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45까지의 숫자만 가능합니다.");
    }

    @Test
    @DisplayName("로또 번호 더미 값 확인")
    void lottoNumberDummyTest() {
        assertThat(LottoNumber.valueOf(-1).getValue()).isEqualTo(-1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 14, 24, 31})
    @DisplayName("적절한 캐싱 값 가져올 수 있는 지 확인")
    void lottoNumbersValueOfTest(int input) {
        assertThat(LottoNumber.valueOf(input).getValue()).isEqualTo(input);
    }

    @Test
    @DisplayName("적절한 캐싱 값들 생성 되었는지 확인")
    void lottoNumberCachingTest() {
        assertThat(LottoNumber.getAllValues().size()).isEqualTo(45);

        assertThat(LottoNumber.getAllValues().contains(LottoNumber.valueOf(1)));
        assertThat(LottoNumber.getAllValues().contains(LottoNumber.valueOf(45)));
    }
}
