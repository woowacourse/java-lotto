package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("유효한 값이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 45})
    void valueOf_validValue_success(final int value) {
        assertThatCode(() -> LottoNumber.valueOf(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 1 ~ 45 가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void valueOf_outOfRange_exceptionThrown(final int value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumber.valueOf(value))
                .withMessageContaining("범위");
    }

    @DisplayName("동일한 객체 비교")
    @Test
    void equals() {
        final LottoNumber lottoNumber = LottoNumber.valueOf(1);
        assertEquals(lottoNumber, LottoNumber.valueOf(1));
    }
}
