package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static domain.LottoMoney.SINGLE_LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LottoMoneyTest {
    @DisplayName("유효한 값이면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "10000", "1000000"})
    void valueOf_validInput_success(final String value) {
        assertThatCode(() -> LottoMoney.valueOf(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : null 입력")
    @Test
    void valueOf_nullInput_exceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.valueOf(null))
                .withMessageContaining("null");
    }

    @DisplayName("객체 생성 실패 : 정수가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "&^*", ""})
    void valueOf_notNumber_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.valueOf(value))
                .withMessageContaining("정수");
    }

    @DisplayName("객체 생성 실패 : 양의 정수가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "-1", "0"})
    void valueOf_notPositiveNumber_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.valueOf(value))
                .withMessageContaining("양의 정수");
    }

    @DisplayName("객체 생성 실패 : 범위에서 벗어난 금액인 경우")
    @ParameterizedTest
    @ValueSource(strings = {"999", "1000001"})
    void valueOf_outOfRange_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.valueOf(value))
                .withMessageContaining("범위를 벗어났습니다");
    }

    @DisplayName("객체 생성 실패 : 로또 한장 단위로 떨어지지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"1001", "99999"})
    void valueOf_divisible_exceptionThrown(final String value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoMoney.valueOf(value))
                .withMessageContaining(SINGLE_LOTTO_PRICE + "원 단위의 금액이 아닙니다");
    }

    @DisplayName("동일한 객체 비교")
    @Test
    void equals() {
        final LottoMoney lottoMoney = LottoMoney.valueOf("1000");
        Assertions.assertThat(lottoMoney)
                .isEqualTo(LottoMoney.valueOf("1000"));
    }
}
