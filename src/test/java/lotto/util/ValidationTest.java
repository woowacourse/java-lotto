package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ValidationTest {

    @Test
    @DisplayName("양수인지 테스트")
    void validate_positive_number() {
        String input = "1";

        assertThatCode(()->Validation.validatePositiveNumber(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음수인지 테스트")
    void throw_validate_positive_number() {
        String input = "-1";

        assertThatThrownBy(()->Validation.validatePositiveNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.1","가",""," "})
    @DisplayName("정수만 가능한지 예외 테스트")
    void throw_validate_integer_number_format(String input){
        assertThatThrownBy(()->Validation.validateIntegerNumberFormat(input))
                .isInstanceOf(NumberFormatException.class);
    }
    @ParameterizedTest
    @ValueSource(strings = {"1","2","3","4"})
    @DisplayName("정수만 가능한지 테스트")
    void validate_integer_number_format(String input){
        assertThatCode(()->Validation.validateIntegerNumberFormat(input))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 한장 단위만큼 입력됐는지 테스트")
    void validate_lotto_unit(){
        String input = "999";

        assertThatThrownBy(()->Validation.validateLottoUnit(input)).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    @DisplayName("로또 한장 단위만큼 입력됐는지 테스트")
    void throw_validate_lotto_unit(){
        String input = "1001";

        assertThatCode(()->Validation.validateLottoUnit(input)).doesNotThrowAnyException();
    }
}