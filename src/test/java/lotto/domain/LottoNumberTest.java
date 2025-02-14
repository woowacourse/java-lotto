package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoNumberTest {

    @DisplayName("번호를 올바르게 생성한다.")
    @Test
    void 번호를_올바르게_생성한다() {
        //given
        LottoNumber lottoNumber = new LottoNumber(5);
        //when
        boolean isEqual = lottoNumber.equals(new LottoNumber(5));
        //then
        assertThat(isEqual).isEqualTo(true);
    }

    @DisplayName("로또 번호는 1부터 45사이가 아니라면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"0", "46", "47"})
    void 로또_번호는_1부터_45사이가_아니라면_예외를_발생한다(int number) {
        //then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(LottoException.class)
                .hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("번호가 같다면 true를 반환한다.")
    @Test
    void 번호가_같다면_true를_반환한다() {
        //then
        assertThat(new LottoNumber(5).equals(new LottoNumber(5))).isEqualTo(true);
    }

    @DisplayName("번호가 다르다면 false를 반환한다.")
    @Test
    void 번호가_다르다면_false를_반환한다() {
        //then
        assertThat(new LottoNumber(5).equals(new LottoNumber(6))).isEqualTo(false);
    }
}
