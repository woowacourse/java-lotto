package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.properties.LottoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @DisplayName("정상적인 로또 숫자 입력 시 객체 정상 생성")
    @Test
    void lottoNumberCreationTest() {
        int number = 30;

        LottoNumber created = LottoNumber.of(number);

        assertThat(created.isSameValue(LottoNumber.of(30)))
                .isTrue();
    }

    @DisplayName("1~45 범위 외의 숫자 입력 시 예외 발생")
    @Test
    void outOfRangeCreationTest() {
        int number = 46;

        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        String.format("로또 번호는 %d부터 %d사이여야 합니다."
                                , LottoProperties.MIN_NUMBER, LottoProperties.MAX_NUMBER)
                );
    }

    @DisplayName("LottoNumber 인스턴스 비교 시 lottoNumber 필드의 값을 비교")
    @Test
    void compareTest() {
        int lower = 10;
        int upper = 30;

        LottoNumber lowerLotto = LottoNumber.of(lower);
        LottoNumber upperLotto = LottoNumber.of(upper);

        assertThat(lowerLotto).isLessThan(upperLotto);
    }
}
