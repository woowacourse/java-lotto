package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void 생성_테스트() {
        assertThat(LottoNumber.generateNumber(5) == LottoNumber.generateNumber(5)).isTrue();
    }

    @Test
    void 숫자_오류_0_테스트() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.generateNumber(0));
    }

    @Test
    void 숫자_오류_46_테스트() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.generateNumber(46));
    }

    @Test
    void 숫자_1_테스트() {
        assertThatCode(() -> {
            LottoNumber.generateNumber(1);
        }).doesNotThrowAnyException();
    }

    @Test
    void 숫자_45_테스트() {
        assertThatCode(() -> {
            LottoNumber.generateNumber(45);
        }).doesNotThrowAnyException();
    }
}
