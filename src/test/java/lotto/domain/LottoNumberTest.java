package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    private static final String NUMBER_RANGE_ERROR = "[ERROR] 1 ~ 45 사이의 숫자를 입력해 주세요.";

    @Test
    @DisplayName("숫자 외의 입력에 대해 에러 발생")
    void lottoNumber_isInteger() {
        assertThatThrownBy(() -> {
            LottoNumber.of("ab");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_RANGE_ERROR);
    }

    @ParameterizedTest
    @DisplayName("숫자가 범위를 넘어가면 에러 발생")
    @ValueSource(ints = {0, 46})
    void lottoNumber_range(Integer number) {
        assertThatThrownBy(() -> {
            LottoNumber.of(number);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_RANGE_ERROR);
    }

    @Test
    @DisplayName("같은 숫자면 같은 LottoNumber 로 판단")
    void lottoNumber() {
        assertThat(LottoNumber.of(5)).isEqualTo(LottoNumber.of(5));
    }

}
