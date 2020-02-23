package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 값이_정상_범위에_있을_때_로또_번호_생성(int value) {
        assertThat(new LottoNumber(value)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 값이_비정상_범위에_있을_때_로또_번호_생성(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("유효하지 않은 로또 번호입니다");
    }
}
