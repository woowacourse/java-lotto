package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest(name = "{0} 로 생성")
    @ValueSource(ints = {1, 45})
    @DisplayName("1 ~ 45 사이 로또 넘버 생성")
    void createLottoNumber(int number) {
        // given & when
        LottoNumber lottoNumber = LottoNumber.getInstance(number);

        // then
        assertThat(lottoNumber.getNumber()).isEqualTo(number);
    }

    @ParameterizedTest(name = "{0} 을 전달했을 때")
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("1 ~ 45 범위 외 숫자가 전달되면, IAE를 던진다")
    void createLottoNumberOutOfRangeShouldFail(int number) {
        assertThatThrownBy(() -> LottoNumber.getInstance(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("\\d+에서 \\d+사이의 값을 입력해주세요");
    }

    @Test
    @DisplayName("LottoNumber 동등성 테스트 - 같은 숫자면 같은 객체 취급")
    void lottoNumberEqualityTest() {
        // given & when
        LottoNumber lottoNumber1 = LottoNumber.getInstance(1);
        LottoNumber lottoNumber2 = LottoNumber.getInstance(1);

        // then
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    @DisplayName("반환값이 캐싱된 값인지 확인")
    void lottoNumberWithSaneValueShouldBeIdentical() {
        // given
        LottoNumber lottoNumber = LottoNumber.getInstance(1);
        LottoNumber lottoNumber2 = LottoNumber.getInstance(1);

        // when
        boolean actual = lottoNumber == lottoNumber2;

        // then
        assertThat(actual).isTrue();
    }
}
