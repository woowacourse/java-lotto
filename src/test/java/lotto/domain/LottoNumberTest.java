package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @DisplayName("로또 번호 범위가 1~45를 벗어난 경우")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void cannotMake(int number) {
        assertThatCode(() -> {
            new LottoNumber(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 범위가 벗어났습니다.");
    }
}