package model;

import static model.Lotto.INVALID_LOTTO_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또 번호가 6자리가 아니라면 예외를 발생시킨다")
    @Test
    void lottoSizeTest() {
        assertAll(
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE),
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(1, 2, 3, 4, 5)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE)
        );
    }

    @DisplayName("로또 번호의 범위가 1~45가 아니라면 예외를 발생시킨다")
    @Test
    void lottoNumberRangeTest() {
        assertAll(
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE),
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE)
        );
    }

    @DisplayName("로또 번호에 중복이 존재한다면 예외를 발생시킨다")
    @Test
    void lottoNumberDuplicateTest() {
        assertThatThrownBy(
                () -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE);
    }
}
