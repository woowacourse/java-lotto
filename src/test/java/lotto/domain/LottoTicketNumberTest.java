package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    void validateNumber_정상적인_숫자일_때(String validNumber) {
        new LottoNumber(validNumber);
    }

    @Test
    void validateNumberScope_숫자가_범위보다_작을_때() {
        String invalidNumberUnderScope = "-11";
        assertThatThrownBy(() -> new LottoNumber(invalidNumberUnderScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVER_SCOPE.getMessage());
    }

    @Test
    void validateNumberScope_숫자가_범위보다_클_때() {
        String invalidNumberOverScope = "50";
        assertThatThrownBy(() -> new LottoNumber(invalidNumberOverScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVER_SCOPE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"천원", "3.2"})
    void validateNumber_정수로_입력하지_않았을_때(String invalidInputMoney) {
        assertThatThrownBy(() -> new LottoNumber(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER.getMessage());
    }
}
