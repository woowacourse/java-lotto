package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberFactoryTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,45})
    void 번호_생성_테스트(int value) {
        LottoNumber lottoNumber = LottoNumberFactory.getInstance(value);
        Assertions.assertThat(lottoNumber).hasFieldOrPropertyWithValue("number", value);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,0,46,200})
    void 번호_예외_테스트(int value) {
        Assertions.assertThatThrownBy(() -> LottoNumberFactory.getInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,45})
    void 번호_비교_테스트(int value) {
        LottoNumber lottoNumber1 = LottoNumberFactory.getInstance(value);
        LottoNumber lottoNumber2 = LottoNumberFactory.getInstance(value);
        Assertions.assertThat(lottoNumber1 == lottoNumber2).isTrue();
    }
}
