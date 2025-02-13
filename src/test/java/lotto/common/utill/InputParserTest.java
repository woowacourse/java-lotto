package lotto.common.utill;

import static lotto.common.utill.InputParser.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @DisplayName("String 입력값을 Int로 변환 가능하다.")
    @Test
    void testParseToInt() {
        String str = "1000";
        assertThat(parseToInt(str)).isEqualTo(1000);
    }

    @DisplayName("문자를 포함할 때, 예외가 발생한다.")
    @Test
    void testParseToIntError() {
        String str = "1000익";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈칸를 포함할 때, 예외가 발생한다.")
    @Test
    void testBlankError() {
        String str = " ";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Null를 포함할 때, 예외가 발생한다.")
    @Test
    void testNullError() {
        String str = "";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }
}