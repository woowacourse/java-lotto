package domain.lottonumbers.lottonumber;

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
        assertThatThrownBy(() -> LottoNumber.of(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45까지의 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 14, 24, 31})
    @DisplayName("적절한 캐싱 값 가져올 수 있는 지 확인")
    void lottoNumbersValueOfTest(int input) {
        assertThat(LottoNumber.of(input).getValue()).isEqualTo(input);
    }
}
