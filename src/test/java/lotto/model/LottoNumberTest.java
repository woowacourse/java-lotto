package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("범위 밖의 숫자 입력시 오류")
    void validate() {
        assertThatThrownBy(() -> {
            new LottoNumber(0);
        }).isInstanceOf(OverRangeException.class)
            .hasMessageContaining("범위를 벗어났습니다.");
    }
}
