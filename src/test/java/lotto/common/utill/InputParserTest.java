package lotto.common.utill;

import static lotto.common.constant.ErrorMessage.*;
import static lotto.common.utill.InputParser.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @DisplayName("String 입력값을 Int 값으로 변환한다.")
    @Test
    void ParseToInt() {
        String str = "1000";
        assertThat(parseToInt(str)).isEqualTo(1000);
    }

    @DisplayName("입력값에 숫자가 아닌 문자를 포함할 경우 예외가 발생한다.")
    @Test
    void ParseToIntErrorWhenIncludeNotDigit() {
        String str = "1000익";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
    }

    @DisplayName("입력값이 빈칸를 포함할 경우 예외가 발생한다.")
    @Test
    void ParseToIntErrorWhenIncludeBlank() {
        String str = " ";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
    }

    @DisplayName("입력값이 Null일 경우, 예외가 발생한다.")
    @Test
    void ParseToIntErrorWhenIncludeNull() {
        String str = "";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
    }
}
