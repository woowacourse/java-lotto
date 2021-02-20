package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceTest {
    @DisplayName("유효한 값이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000", "1000000"})
    void valueOf_validInput_success(final String value) {
        assertThatCode(() -> Price.valueOf(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : null 입력")
    @Test
    void valueOf_nullInput_exceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.valueOf(null))
                .withMessageContaining("null");
    }

    @DisplayName("객체 생성 실패 : 정수가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "&^*", ""})
    void valueOf_notNumber_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.valueOf(value))
                .withMessageContaining("정수");
    }

    @DisplayName("객체 생성 실패 : 양의 정수가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "-1", "0"})
    void valueOf_notPositiveNumber_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.valueOf(value))
                .withMessageContaining("양의 정수");
    }

    @DisplayName("객체 생성 실패 : 1000원 미만, 100만원 초과의 금액인 경우")
    @ParameterizedTest
    @ValueSource(strings = {"999", "1000001"})
    void valueOf_outOfRange_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.valueOf(value))
                .withMessageContaining("범위를 벗어났습니다.");
    }

    @DisplayName("객체 생성 실패 : 1000원 단위로 떨어지지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"1001", "99999"})
    void valueOf_divisible_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Price.valueOf(value))
                .withMessageContaining("1000원 단위 금액이 아닙니다.");
    }

    @DisplayName("동일한 객체 비교")
    @Test
    void equals() {
        final Price price = Price.valueOf("1000");
        assertEquals(price, Price.valueOf("1000"));
    }
}