package model;

import static model.Lotto.INVALID_LOTTO_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class LottoTest {

    @DisplayName("로또 번호가 6자리가 아니라면 예외를 발생시킨다")
    @Test
    void lottoSizeTest() {
        assertAll(
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(1,2,3,4,5,6,7)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE),
                () -> assertThatThrownBy(
                        () -> new Lotto(List.of(1,2,3,4,5)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(INVALID_LOTTO_ERROR_MESSAGE)
        );
    }
}
