package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 로또_번호는_1부터_45사이여야_한다(int lottoNumber) {
        assertThatCode(() -> new LottoNumber(lottoNumber)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void 로또_번호가_1부터_45사이가_아니면_예외가_발생한다(int lottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
