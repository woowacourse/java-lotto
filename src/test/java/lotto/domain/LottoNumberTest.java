package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    void 정상적인_숫자일_때(String validNumber) {
        new LottoNumber(validNumber);
    }

    @Test
    void validateNumberScope_숫자가_범위보다_작을_때() {
        String invalidNumberUnderScope = "-11";
        assertThatThrownBy(() -> new LottoNumber(invalidNumberUnderScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");
    }

    @Test
    void validateNumberScope_숫자가_범위보다_클_때() {
        String invalidNumberOverScope = "50";
        assertThatThrownBy(() -> new LottoNumber(invalidNumberOverScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 범위가 잘못되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"천원", "3.2"})
    void validateNumber_정수로_입력하지_않았을_때(String invalidInputMoney) {
        assertThatThrownBy(() -> new LottoNumber(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수로 입력하셔야 합니다.");
    }

    @ParameterizedTest
    @EmptySource
    void validateNumber_빈_문자를_입력했을_때(String emptyValue) {
        assertThatThrownBy(() -> new LottoNumber(emptyValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정수로 입력하셔야 합니다.");
    }

    @ParameterizedTest
    @NullSource
    void validateNotNull_NULL을_입력했을_때(String nullValue) {
        assertThatThrownBy(() -> new LottoNumber(nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("로또 번호가 입력되지 않았습니다.");
    }
}
