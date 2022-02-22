package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest(name = "1 ~ 45 사이 로또 넘버 생성 : {0}")
    @ValueSource(ints = {1, 45})
    void createLottoNumber(int number) {
        // given & when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    @ParameterizedTest(name = "1 ~ 45 범위 외 숫자가 전달되면, IAE를 던진다")
    @ValueSource(ints = {-1, 0, 46})
    void createLottoNumberOutOfRangeShouldFail(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("1에서 45 사이의 값을 입력해주세요");
    }

    @Test
    void lottoNumberEqualityTest() {
        // given & when
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);

        // then
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
